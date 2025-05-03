package com.example.telecom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/site-events")
public class SiteEventController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public ResponseEntity<String> publishEvent(@RequestBody SiteEvent event) throws JsonProcessingException {
        String msg = mapper.writeValueAsString(event);
        kafkaTemplate.send("site-events", event.getSiteId(), msg);
        return ResponseEntity.ok("Event sent to Kafka");
    }
    
    @GetMapping
    public ResponseEntity<List<String>> getMessage(){
    	String strHello = "hello";
    	String strBye = "Bye";
    	List<String> messages = new ArrayList<>();
    	messages.add(strHello);
    	messages.add(strBye);
    	return ResponseEntity.ok(messages);
    }
    
}