package com.example.natwest_project.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReportGenerationServiceTest {

    private ReportGenerationService reportGenerationService;
    private Path tempDirectory;

    @BeforeEach
    public void setUp() throws IOException {
        tempDirectory = Files.createTempDirectory("report-test");

        CsvFileService csvFileService = new CsvFileService();
        TransformationService transformationService = new TransformationService();
        ReportService reportService = new ReportService();

        reportGenerationService = new ReportGenerationService(csvFileService, transformationService, reportService);

        ReflectionTestUtils.setField(reportGenerationService, "fileSetsDirectory", tempDirectory.toString());

        prepareTestData();
    }

    private void prepareTestData() throws IOException {
        Path setDir = tempDirectory.resolve("set1");
        Files.createDirectories(setDir);

        try (FileWriter writer = new FileWriter(setDir.resolve("input.csv").toFile())) {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "field1", "field2", "field3", "field4", "field5", "refkey1", "refkey2"));
            csvPrinter.printRecord("value1", "value2", 3.0, "value4", 10.0, "refkey1", "refkey2");
            csvPrinter.flush();
        }

        try (FileWriter writer = new FileWriter(setDir.resolve("reference.csv").toFile())) {
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
                    "refkey1", "refdata1", "refkey2", "refdata2", "refdata3", "refdata4"));
            csvPrinter.printRecord("refkey1", "refdata1", "refkey2", "refdata2", "refdata3", 10.0);
            csvPrinter.flush();
        }
    }

    @Test
    public void testGenerateReports() {
        reportGenerationService.generateReports();

        Path outputFilePath = tempDirectory.resolve("set1").resolve("output.csv");
        assertTrue(Files.exists(outputFilePath), "Output file should be created");
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.walk(tempDirectory)
                .sorted((a, b) -> b.compareTo(a))
                .map(Path::toFile)
                .forEach(File::delete);
    }
}
