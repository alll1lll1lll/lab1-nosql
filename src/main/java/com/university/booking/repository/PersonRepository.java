package com.university.booking.repository;

import com.university.booking.enums.RedisKeyPrefix;
import com.university.booking.model.Person;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository extends AbstractRedisRepository<Person> {

    public PersonRepository(RedisTemplate<String, Object> redisTemplate) {
        super(redisTemplate, RedisKeyPrefix.PERSON, Person.class);
    }

    public Person save(Person person) {
        return super.save(person.getId(), person);
    }
}
