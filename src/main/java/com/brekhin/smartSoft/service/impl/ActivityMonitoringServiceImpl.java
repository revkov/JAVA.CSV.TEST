package com.brekhin.smartSoft.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.brekhin.smartSoft.model.ActivityMonitoring;
import com.brekhin.smartSoft.repository.ActivityMonitoringRepository;
import com.brekhin.smartSoft.service.ActivityMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.UUID;

@Service
public class ActivityMonitoringServiceImpl implements ActivityMonitoringService {

    private ActivityMonitoringRepository activityMonitoringRepository;
    private static Logger LOG = LoggerFactory.getLogger(ActivityMonitoringServiceImpl.class);

    @Autowired
    public ActivityMonitoringServiceImpl(ActivityMonitoringRepository activityMonitoringRepository) {
        this.activityMonitoringRepository = activityMonitoringRepository;
    }

    @Override
    public void saveActivityMonitoringDataToDB() {
        LinkedList<ActivityMonitoring> acmList = new LinkedList<>();
        int count = 0;
        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/test_case.csv"), ';', '"', 1)) {
            String[] data;
            while ((data = reader.readNext()) != null) {
                count++;
                if (data != null) {
                    ActivityMonitoring activityMonitoring = new ActivityMonitoring()
                            .setSsoid(data[0])
                            .setTs(Long.parseLong(data[1]))
                            .setGrp(data[2])
                            .setType(data[3])
                            .setSubtype(data[4])
                            .setUrl(data[5])
                            .setOrgid(data[6])
                            .setFormid(data[7])
                            .setCode(data[8])
                            .setLtpa(data[9])
                            .setSudirresponse(data[10])
                            .setYmdh(getDateFromString(data[11]));

                    acmList.add(activityMonitoring);
                }
            }
            activityMonitoringRepository.saveAll(acmList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public LocalDateTime getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
        return LocalDateTime.parse(date, formatter);
    }
}
