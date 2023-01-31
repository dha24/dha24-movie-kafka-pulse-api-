package com.kafkapulse.moviekafkapulseapi.controllers;

import com.kafkapulse.moviekafkapulseapi.models.TopicResponse;
import com.kafkapulse.moviekafkapulseapi.service.PublishMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/pulseToKafka")
public class MoviePulseController {

    @Autowired
    private PublishMessageService publishMessageService;
    @GetMapping("/kafkaStatus")
    public ResponseEntity<String> testKafkaStatus(){
        return new ResponseEntity<String>("Hello From Kafka!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TopicResponse> movieEventPulseToKafka(@RequestBody Map<String, String> payload){
        System.out.println("Publishing:"+payload.get("messageToPublish"));
        return new ResponseEntity<TopicResponse>( publishMessageService.sendMessages(payload.get("messageToPublish")), HttpStatus.OK);
    }
}
