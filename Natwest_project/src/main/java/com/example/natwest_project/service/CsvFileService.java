package com.example.natwest_project.service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CsvFileService {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileService.class);
    public List<CSVRecord> readCsvFile(Path filePath) throws IOException {

        logger.info("Reading CSV file from path: {}", filePath);
        try (FileReader reader = new FileReader(filePath.toFile());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            return new ArrayList<>(csvParser.getRecords());
        }
    }
}
