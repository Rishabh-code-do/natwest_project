package com.example.natwest_project.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);
    public void generateReport(List<Map<String, String>> transformedRecords, Path outputPath) throws IOException {
        try (FileWriter writer = new FileWriter(outputPath.toFile());
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                     "outfield1", "outfield2", "outfield3", "outfield4", "outfield5"))) {
            for (Map<String, String> record : transformedRecords) {
                csvPrinter.printRecord(
                        record.get("outfield1"),
                        record.get("outfield2"),
                        record.get("outfield3"),
                        record.get("outfield4"),
                        record.get("outfield5")
                );
            }
        }
        logger.info("Report generated at: {}", outputPath);
    }
}
