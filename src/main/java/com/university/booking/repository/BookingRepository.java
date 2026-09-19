package com.university.booking.repository;

import com.university.booking.enums.RedisKeyPrefix;
import com.university.booking.model.Booking;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookingRepository extends AbstractRedisRepository<Booking> {

    public BookingRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, RedisKeyPrefix.BOOKING, Booking.class);
    }

    public Booking save(Booking booking) {
        return super.save(booking.getId(), booking);
    }
}
