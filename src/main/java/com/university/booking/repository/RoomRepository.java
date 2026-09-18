package com.university.booking.repository;

import com.university.booking.enums.RedisKeyPrefix;
import com.university.booking.model.Room;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository extends AbstractRedisRepository<Room> {

    public RoomRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, RedisKeyPrefix.ROOM, Room.class);
    }

    public Room save(Room room) {
        return super.save(room.getId(), room);
    }
}
