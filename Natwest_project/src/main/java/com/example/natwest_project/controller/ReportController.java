package com.example.natwest_project.controller;
import com.example.natwest_project.service.ReportGenerationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportGenerationService reportGenerationService;

    private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

    public ReportController(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }
    @PostMapping("/generate")
    public ResponseEntity<?> generateReports() {
        logger.info("Manual report generation triggered via API.");
        try {
            reportGenerationService.generateReports();
            return new ResponseEntity<>("Report Generated Successfully.", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error Generated While Generating Report.", HttpStatus.NOT_FOUND);
        }

    }
}
