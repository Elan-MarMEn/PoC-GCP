package com.gcp.poc.reader.datastore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.gcp.poc.reader.entity.RecordFile;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Log4j2
@Component
public class DataStoreProcess {

    private final Firestore db = FirestoreOptions.getDefaultInstance().getService();
    private DocumentReference documentReference;

    public void addDocument(RecordFile recordFile) throws ExecutionException, InterruptedException {
        documentReference = db.collection("ProcessedFiles").document(recordFile.getFilename());
        ApiFuture<WriteResult> result= documentReference.set(mapToProcessFile(recordFile));
        log.error("AddedTime"+ result.get().toString());
    }

    public Map<String,Object> mapToProcessFile(RecordFile recordFile){
        Map<String,Object> mapper = new HashMap<>();
        mapper.put("filename",recordFile.getFilename());
        mapper.put("Finished",recordFile.getFinished());
        mapper.put("Length", recordFile.getLength());
        return mapper;
    }
}
