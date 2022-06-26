package com.redis.springredis.dto;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RedisDataRepository extends CrudRepository<RedisData, String> {

    Optional<RedisData> findById(String id);

    List<RedisData> findByType(String type);

}
