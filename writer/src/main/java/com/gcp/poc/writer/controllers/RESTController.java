package com.gcp.poc.writer.controllers;

import com.gcp.poc.writer.entity.RecordFile;
import com.gcp.poc.writer.services.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("writer")
public class RESTController {

    @Autowired
    WriterService writerService;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody RecordFile body){
        writerService.publishMessage(body);
        return new ResponseEntity<>(org.springframework.http.HttpStatus.ACCEPTED);
    }
}
