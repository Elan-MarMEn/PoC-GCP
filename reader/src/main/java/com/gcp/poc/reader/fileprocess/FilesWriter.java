package com.gcp.poc.reader.fileprocess;


import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class FilesWriter {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private Bucket bucket;

    public void setBucket(String bucketName) {
        this.bucket = storage.get(bucketName);
        System.out.println(bucket.getName());
    }

    public void deleteInfo(String filename){
        Blob blob = bucket.get(filename);
        log.info("the object:"+filename+
                "content: "+ new String(blob.getContent()));
        blob.delete();
    }
}
