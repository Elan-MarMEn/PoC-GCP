package com.gcp.poc.writer.entity;

import com.google.api.client.json.Json;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.json.JSONParser;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RecordFile {

    private String filename;
    private Boolean finished;
    private int length;
}
