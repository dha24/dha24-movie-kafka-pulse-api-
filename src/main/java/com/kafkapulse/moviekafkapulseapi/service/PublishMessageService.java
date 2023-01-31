package com.kafkapulse.moviekafkapulseapi.service;

import com.kafkapulse.moviekafkapulseapi.models.TopicResponse;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
/*
1. Add the spring-kafka dependency to your pom.xml file.
2. Configure a KafkaTemplate bean in your Spring Boot application configuration class.
3. Inject the KafkaTemplate into your service class.
4. Use the KafkaTemplate's send method to send messages to a topic, and wrap the send method with a CompletableFuture.
 */
@Service
public class PublishMessageService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

public TopicResponse sendMessages(String message) {
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("movie-mania-detector", message);
    TopicResponse topicResponse;
    try {
        RecordMetadata recordMetadata = CompletableFuture.completedFuture(future).get().get().getRecordMetadata();

        topicResponse = TopicResponse.builder()
                .toTopic(recordMetadata.topic())
                .toPartition(recordMetadata.partition())
                .toOffset(recordMetadata.offset())
                .build();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    } catch (ExecutionException e) {
        throw new RuntimeException(e);
    }
    return topicResponse;
}


}
