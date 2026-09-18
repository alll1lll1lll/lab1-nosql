package com.university.booking.service;

import com.university.booking.enums.RedisKeyPrefix;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
public class RateLimiterService {

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean isAllowed(String clientIp, int maxRequestsPerMinute) {
        String key = RedisKeyPrefix.RATE_LIMIT.getKey(clientIp);
        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count == 1)
            redisTemplate.expire(key, Duration.ofMinutes(1));

        return count != null && count <= maxRequestsPerMinute;
    }
}
