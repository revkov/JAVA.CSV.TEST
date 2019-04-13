package com.brekhin.smartSoft.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.brekhin.smartSoft.model.ActivityMonitoringEntity;
import com.brekhin.smartSoft.repository.projection.IFrequentlyUsedFormsTOProjection;
import com.brekhin.smartSoft.repository.ActivityMonitoringRepository;
import com.brekhin.smartSoft.service.ActivityMonitoringService;
import com.brekhin.smartSoft.to.out.ActivityInterruptedTO;
import com.brekhin.smartSoft.to.out.FormsUsedInLastHourTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ActivityMonitoringServiceImpl implements ActivityMonitoringService {

    private final ActivityMonitoringRepository activityMonitoringRepository;
    private static Logger LOG = LoggerFactory.getLogger(ActivityMonitoringServiceImpl.class);

    @Autowired
    public ActivityMonitoringServiceImpl(ActivityMonitoringRepository activityMonitoringRepository) {
        this.activityMonitoringRepository = activityMonitoringRepository;
    }

    @Override
    public void saveActivityMonitoringDataToDB() {
        LinkedList<ActivityMonitoringEntity> acmList = new LinkedList<>();

        try (CSVReader reader = new CSVReader(new FileReader("src/main/resources/test_case.csv"), ';', '"', 1)) {
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
            activityMonitoringRepository.saveAll(acmList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<IFrequentlyUsedFormsTOProjection> get5FrequentlyUsedForms() {
        List<IFrequentlyUsedFormsTOProjection> frequentlyUsedForms = activityMonitoringRepository.get5FrequentlyUsedForms();
        LOG.info("\nSIZE(get5FrequentlyUsedForms):" + frequentlyUsedForms.size() + "\n");
        return frequentlyUsedForms;
    }

    @Override
    public List<FormsUsedInLastHourTO> getFormsUsedInLastHour() {

        List<ActivityMonitoringEntity> all = activityMonitoringRepository.findAll();
        List<FormsUsedInLastHourTO> result = new ArrayList<>();
        for (ActivityMonitoringEntity actM : all) {
            LocalDateTime toDateTime =
                    LocalDateTime.ofInstant(Instant.ofEpochSecond(actM.getTs()),
                            TimeZone.getDefault().toZoneId());
            LocalDateTime fromDateTime = actM.getYmdh();

            LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);

            long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
            tempDateTime = tempDateTime.minusHours(hours);

            long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
            tempDateTime = tempDateTime.minusMinutes(minutes);

            long seconds = tempDateTime.until(toDateTime, ChronoUnit.SECONDS);

            if (Math.abs(hours) * 60 + Math.abs(minutes) + (Math.abs(seconds) / 60) < 60) {
                result.add(new FormsUsedInLastHourTO(actM.getSsoid(), actM.getFormid()));
            }
        }

        LOG.info("\nSIZE(getFormsUsedInLastHour):" + result.size() + "\n");

        return result;
    }

    @Override
    public List<ActivityInterruptedTO> getInterruptedActivities() {
        List<ActivityMonitoringEntity> monitoringList = activityMonitoringRepository.findAllOrderByTs();
        HashMap<String, List<String>> personActivity = new HashMap<>();
        List<ActivityInterruptedTO> userActivity = new ArrayList<>();

        for (ActivityMonitoringEntity am : monitoringList) {
            if (!personActivity.containsKey(am.getSsoid())) {
                personActivity.put(am.getSsoid(), new ArrayList<>());
                personActivity.get(am.getSsoid()).add(am.getSubtype());
            } else {
                personActivity.get(am.getSsoid()).add(am.getSubtype());
            }
        }

        for (String s : personActivity.keySet()) {
            List<String> strings = personActivity.get(s);
            if (!strings.contains("success")) {
                userActivity.add(new ActivityInterruptedTO(s, strings.get(strings.size() - 1)));
            }
        }

        LOG.info("\n SIZE(getInterruptedActivities): " + Integer.toString(userActivity.size()) + "\n");
        return userActivity;
    }


    private LocalDateTime getDateFromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
        return LocalDateTime.parse(date, formatter);
    }
}
