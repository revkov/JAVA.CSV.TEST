package com.brekhin.smartSoft.util;

import au.com.bytecode.opencsv.CSVReader;
import com.brekhin.smartSoft.model.ActivityMonitoringEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class CSVToEntityUtil {

    public static LinkedList<ActivityMonitoringEntity> convertCSVToEntitiesList(String fileName) {
        LinkedList<ActivityMonitoringEntity> acmList = new LinkedList<>();

        Class clazz = CSVToEntityUtil.class;
        InputStream inputStream = clazz.getResourceAsStream("/" + fileName);

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream, "UTF-8"), ';', '"', 1)) {
            String[] data;
            while ((data = reader.readNext()) != null) {
                if (data != null) {
                    ActivityMonitoringEntity activityMonitoring = new ActivityMonitoringEntity()
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return acmList;
    }

    private static LocalDateTime getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
        return LocalDateTime.parse(date, formatter);
    }

}
