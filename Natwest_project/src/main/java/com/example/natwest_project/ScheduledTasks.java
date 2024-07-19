package com.example.natwest_project;
import com.example.natwest_project.service.ReportGenerationService;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    private final ReportGenerationService reportGenerationService;

    public ScheduledTasks(ReportGenerationService reportGenerationService) {
        this.reportGenerationService = reportGenerationService;
    }

    //@Scheduled(cron = "0 30 14 * * ?") - At 14:30 (2:30 PM) every day
    //@Scheduled(cron = "0 15 7 ? * MON") - At 07:15 (7:15 AM) every Monday 
    // Can also use above schedular 
    
    @Scheduled(cron = "0 0 * * * ?")  // Run every hour
    public void scheduleReportGeneration() {
        logger.info("Scheduled report generation triggered.");
        reportGenerationService.generateReports();
        logger.info("Scheduled report generation completed.");
    }
}
