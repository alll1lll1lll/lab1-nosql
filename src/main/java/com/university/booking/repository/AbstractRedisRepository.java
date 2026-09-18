package com.university.booking.repository;

import com.university.booking.enums.RedisKeyPrefix;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractRedisRepository<T> {

    protected final RedisTemplate<String, Object> redisTemplate;
    protected final RedisKeyPrefix keyPrefix;
    protected final Class<T> entityClass;

    public AbstractRedisRepository(RedisTemplate<String, Object> redisTemplate,
                                   RedisKeyPrefix keyPrefix, Class<T> entityClass) {

        this.redisTemplate = redisTemplate;
        this.keyPrefix = keyPrefix;
        this.entityClass = entityClass;
    }

    public T save(String id, T entity) {
        redisTemplate.opsForValue().set(keyPrefix.getKey(id), entity);

        return entity;
    }

    public Optional<T> findById(String id) {
        Object obj = redisTemplate.opsForValue().get(keyPrefix.getKey(id));

        return Optional.ofNullable(entityClass.cast(obj));
    }

    public List<T> findAll() {
        Set<String> keys = redisTemplate.keys(keyPrefix.getPrefix() + "*");

        if (keys == null || keys.isEmpty())
            return Collections.emptyList();

        List<Object> results = redisTemplate.opsForValue().multiGet(keys);

        if (results == null)
            return Collections.emptyList();

        return results.stream()
                .filter(Objects::nonNull)
                .map(entityClass::cast)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        redisTemplate.delete(keyPrefix.getKey(id));
    }

}
