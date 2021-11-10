package com.gcp.poc.reader.config;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.gson.Gson;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;
import com.gcp.poc.reader.datastore.DataStoreProcess;
import com.gcp.poc.reader.entity.RecordFile;
import com.gcp.poc.reader.fileprocess.FilesWriter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Log4j2
@Component
public class PubSubSubscriber {

    private TopicName topicName;
    private ProjectSubscriptionName subscriptionName;
    private Subscriber subscriber;
    private Gson gson = new Gson();

    @Autowired
    DataStoreProcess dataStoreProcess;

    public PubSubSubscriber() {
        subscriptionName = ProjectSubscriptionName.of("opportune-eye-331017","subs-poc");
    }

    public void startListeningMsg(FilesWriter filesWriter) {
        subscriber = Subscriber.newBuilder(subscriptionName,receiveMessage(filesWriter)).build();
        subscriber.startAsync().awaitRunning();
        log.info("Listening for messages");
    }

    private MessageReceiver receiveMessage(FilesWriter filesWriter){
        return (PubsubMessage message, AckReplyConsumer consumer) -> {
            String jsonmessage = message.getData().toStringUtf8();
            RecordFile recordFile = gson.fromJson(jsonmessage, RecordFile.class);
            filesWriter.deleteInfo(recordFile.getFilename());
            recordFile.setFinished(true);
            try {
                dataStoreProcess.addDocument(recordFile);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        };
    }
}
