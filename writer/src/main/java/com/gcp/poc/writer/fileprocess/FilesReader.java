package com.gcp.poc.writer.fileprocess;


import com.google.cloud.storage.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.nio.file.Path;
@Component
public class FilesReader {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private Bucket bucket;

    public void setBucket(String bucketName) {
        this.bucket = storage.get(bucketName);
        System.out.println(bucket.getName());
    }

    public String getInfo(String filename){
        Blob blob = bucket.get(filename);
        return new String(blob.getContent());
    }

    public void copyInfo(String filename){
        Blob blob = bucket.get(filename);
        blob.copyTo("opportune-eye-331017.appspot.com");
    }
}
