package com.gcp.poc.writer.services;

import com.gcp.poc.writer.entity.RecordFile;

public interface WriterService {

    void publishMessage(RecordFile message);
}
