package com.redis.springredis.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisSubscriber implements MessageListener {
    @Autowired
    private RedisTemplate<String, RedisData> redisTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer<?> redisSerializer = redisTemplate.getValueSerializer();
        RedisData redisData = (RedisData) redisSerializer.deserialize(message.getBody());
        log.info(".:Data Received from Channel Data:.");
        log.info("Id : "+redisData.getId());
        log.info("Name : "+redisData.getName());
        log.info("Type : "+redisData.getType());
        log.info("Data : "+redisData.getData());

    }
}
