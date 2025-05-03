package com.example.telecom;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SiteEventConsumer {

    @Autowired
    private RedisTemplate<String, SiteEvent> redisTemplate;

    @KafkaListener(topics = "site-events", groupId = "telecom-group")
    public void consume(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SiteEvent event = mapper.readValue(message, SiteEvent.class);
            redisTemplate.opsForValue().set("site:" + event.getSiteId(), event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}