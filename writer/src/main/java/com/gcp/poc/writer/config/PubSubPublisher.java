package com.gcp.poc.writer.config;

import com.google.api.client.util.Value;
import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Log4j2
@Component
public class PubSubPublisher {

    private TopicName topicName;
    private Publisher publisher;


    public PubSubPublisher() throws IOException {
        topicName = TopicName.of("opportune-eye-331017","topic-poc");
        publisher = Publisher.newBuilder(topicName).build();
    }

    public void sendMessage(String message) throws ExecutionException, InterruptedException {
        ByteString data = ByteString.copyFromUtf8(message);
        PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
        ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
        log.info("Message was sent: MessageID:"+ messageIdFuture.get());
    }
}
