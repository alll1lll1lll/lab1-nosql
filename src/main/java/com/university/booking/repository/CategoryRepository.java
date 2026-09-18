package com.university.booking.repository;

import com.university.booking.enums.RedisKeyPrefix;
import com.university.booking.model.Category;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends AbstractRedisRepository<Category> {

    public CategoryRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, RedisKeyPrefix.CATEGORY, Category.class);
    }

    public Category save(Category category) {
        return super.save(category.getId(), category);
    }
}
