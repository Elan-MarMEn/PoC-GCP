package com.gcp.poc.reader.services.imp;

import com.gcp.poc.reader.config.PubSubSubscriber;
import com.gcp.poc.reader.datastore.DataStoreProcess;
import com.gcp.poc.reader.entity.RecordFile;
import com.gcp.poc.reader.fileprocess.FilesWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcp.poc.reader.services.Reader;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ReaderImp implements Reader {

    @Autowired
    PubSubSubscriber pubSubSubscriber;

    @Autowired
    FilesWriter filesWriter;

    @Autowired
    DataStoreProcess dataStoreProcess;

    @Override
    public void startSubscriber() {
        filesWriter.setBucket("opportune-eye-331017.appspot.com");
        pubSubSubscriber.startListeningMsg(filesWriter);
    }

    @Override
    public void addsome(){
        try {
            dataStoreProcess.addDocument(new RecordFile("test",true,30));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
