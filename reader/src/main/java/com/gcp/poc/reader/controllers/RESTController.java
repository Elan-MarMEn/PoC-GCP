package com.gcp.poc.reader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gcp.poc.reader.services.Reader;

@RestController
@RequestMapping("reader")
public class RESTController {

    @Autowired
    Reader reader;

    @PostMapping
    public ResponseEntity<String> sendMessage(){
        reader.startSubscriber();
        return new ResponseEntity<>(org.springframework.http.HttpStatus.ACCEPTED);
    }
}
