package com.example.natwest_project.service;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportGenerationService {
    private static final Logger logger = LoggerFactory.getLogger(ReportGenerationService.class);

    private final CsvFileService csvFileService;
    private final TransformationService transformationService;
    private final ReportService reportService;

    @Value("${report.file-sets-directory}")
    private String fileSetsDirectory;

    public ReportGenerationService(CsvFileService csvFileService, TransformationService transformationService, ReportService reportService) {
        this.csvFileService = csvFileService;
        this.transformationService = transformationService;
        this.reportService = reportService;
    }

    public void generateReports() {
        try {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(fileSetsDirectory));

            for (Path setDir : directoryStream) {
                if (Files.isDirectory(setDir)) {
                    String setId = setDir.getFileName().toString();
                    Path inputFilePath = setDir.resolve("input.csv");
                    Path referenceFilePath = setDir.resolve("reference.csv");
                    Path outputFilePath = setDir.resolve("output.csv");

                    List<CSVRecord> inputRecords = csvFileService.readCsvFile(inputFilePath);
                    List<CSVRecord> referenceRecords = csvFileService.readCsvFile(referenceFilePath);

                    Map<String, CSVRecord> referenceData = referenceRecords.stream()
                            .collect(Collectors.toMap(
                                    record -> record.get("refkey1") + record.get("refkey2"),
                                    record -> record
                            ));

                    List<Map<String, String>> transformedRecords = inputRecords.stream()
                            .map(record -> transformationService.transformRecord(record, referenceData))
                            .collect(Collectors.toList());

                    reportService.generateReport(transformedRecords, outputFilePath);
                }
            }
        } catch (IOException e) {
            logger.error("Error generating reports for all sets", e);
            throw new RuntimeException("Failed to generate reports for all sets", e);
        }
    }
}
