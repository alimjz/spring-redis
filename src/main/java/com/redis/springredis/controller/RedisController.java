package com.redis.springredis.controller;

import com.redis.springredis.dto.RedisData;
import com.redis.springredis.dto.RedisDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    private RedisDataRepository redisDataRepository;

    @Autowired
    private RedisTemplate<String,RedisData> redisTemplate;

    @RequestMapping(method = RequestMethod.POST, path = "/redis/save")
    public ResponseEntity saveToRedis(@RequestBody RedisData redisData){
        redisDataRepository.save(redisData);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/redis/read/type/{type}")
    public List<RedisData> readByType(@PathVariable String type){
        return redisDataRepository.findByType(type);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/redis/read/id/{id}")
    public RedisData readById(@PathVariable String id){
        return redisDataRepository.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/redis/pub")
    public ResponseEntity push(@RequestBody RedisData redisData){
        String pubSubChannel = "redis-data";
        redisDataRepository.save(redisData);
        redisTemplate.convertAndSend(pubSubChannel,redisData);
        return ResponseEntity.ok().build();
    }

}
