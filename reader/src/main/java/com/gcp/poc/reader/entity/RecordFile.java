package com.gcp.poc.reader.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class RecordFile {

    private String filename;
    private Boolean finished;
    private int length;
}
