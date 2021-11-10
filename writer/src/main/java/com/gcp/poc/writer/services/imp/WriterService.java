package com.gcp.poc.writer.services.imp;

import com.gcp.poc.writer.config.PubSubPublisher;
import com.gcp.poc.writer.entity.RecordFile;
import com.gcp.poc.writer.fileprocess.FilesReader;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
@Log4j2
@Service
public class WriterService implements com.gcp.poc.writer.services.WriterService {

    @Autowired private PubSubPublisher publisher;

    @Autowired private FilesReader filesReader;

    @Override
    public void publishMessage(RecordFile recordFile){
        filesReader.setBucket("bucket-marin");
        filesReader.copyInfo(recordFile.getFilename());
        try {
            publisher.sendMessage(toJson(recordFile));
        } catch (ExecutionException | InterruptedException e) {
            log.error("Something was wrong to send message");
            e.printStackTrace();
        }
    }

    public String toJson(Object o){
        Gson gson = new Gson();
        return gson.toJson(o);
    }
}
