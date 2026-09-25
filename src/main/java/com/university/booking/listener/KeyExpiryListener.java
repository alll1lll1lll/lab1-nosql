package com.university.booking.listener;

import com.university.booking.enums.RedisKeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Исследование сценария «потеря временных ключей»:
 * подписывается на __keyevent@0__:expired и фиксирует каждый факт истечения.
 *
 * Redis публикует сообщение в этот канал после того, как ключ был удалён по TTL
 * (lazy expiry при обращении или active expiry фоновым потоком).
 * Тело сообщения — имя удалённого ключа; значение уже недоступно.
 */
@Slf4j
@Component
public class KeyExpiryListener implements MessageListener {

    private final Map<String, AtomicLong> expiredByType = new ConcurrentHashMap<>();

    @Override
    public void onMessage(@NonNull Message message, byte[] pattern) {
        String expiredKey = new String(message.getBody());
        String keyType = resolveType(expiredKey);

        expiredByType.computeIfAbsent(keyType, k -> new AtomicLong()).incrementAndGet();

        log.info("[keyspace] expired key={} type={} totalExpiredByType={}",
                expiredKey, keyType, expiredByType);
    }

    private String resolveType(String key) {
        return Arrays.stream(RedisKeyPrefix.values())
                .filter(p -> key.startsWith(p.getPrefix()))
                .map(p -> p.name().toLowerCase())
                .findFirst()
                .orElse("unknown");
    }

    public Map<String, Long> getExpiredStats() {
        Map<String, Long> snapshot = new ConcurrentHashMap<>();
        expiredByType.forEach((type, count) -> snapshot.put(type, count.get()));
        return snapshot;
    }
}
