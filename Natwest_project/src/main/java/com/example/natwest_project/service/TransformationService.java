package com.example.natwest_project.service;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TransformationService {
    private static final Logger logger = LoggerFactory.getLogger(TransformationService.class);
    public Map<String, String> transformRecord(CSVRecord record, Map<String, CSVRecord> referenceData) {
        Map<String, String> transformedRecord = new HashMap<>();

        String refKey1 = record.get("refkey1");
        String refKey2 = record.get("refkey2");
        CSVRecord refRecord = referenceData.get(refKey1 + refKey2);

        transformedRecord.put("outfield1", record.get("field1") + record.get("field2"));
        transformedRecord.put("outfield2", refRecord.get("refdata1"));
        transformedRecord.put("outfield3", refRecord.get("refdata2") + refRecord.get("refdata3"));
        transformedRecord.put("outfield4", String.valueOf(
                Double.parseDouble(record.get("field3")) * Math.max(
                        Double.parseDouble(record.get("field5")),
                        Double.parseDouble(refRecord.get("refdata4"))
                )
        ));
        transformedRecord.put("outfield5", String.valueOf(
                Math.max(
                        Double.parseDouble(record.get("field5")),
                        Double.parseDouble(refRecord.get("refdata4"))
                )
        ));
        logger.info("Records Transformed");
        return transformedRecord;
    }
}
