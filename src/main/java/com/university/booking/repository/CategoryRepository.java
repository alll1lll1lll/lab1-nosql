package com.university.booking.repository;

import com.university.booking.model.Category;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CategoryRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String KEY_PREFIX = "category:";

    public CategoryRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Category save(Category category) {
        String key = KEY_PREFIX + category.getId();
        redisTemplate.opsForValue().set(key, category);
        return category;
    }

    public Optional<Category> findById(String id) {
        String key = KEY_PREFIX + id;
        Category category = (Category) redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(category);
    }

    public List<Category> findAll() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");

        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }

        List<Object> results = redisTemplate.opsForValue().multiGet(keys);

        if (results == null) {
            return Collections.emptyList();
        }

        return results.stream()
                .filter(Objects::nonNull)
                .map(obj -> (Category) obj)
                .collect(Collectors.toList());
    }

    public void delete(String id) {
        String key = KEY_PREFIX + id;
        redisTemplate.delete(key);
    }
}
