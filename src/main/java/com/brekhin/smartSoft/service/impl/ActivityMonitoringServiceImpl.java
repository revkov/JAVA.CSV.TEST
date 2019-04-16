package com.brekhin.smartSoft.service.impl;

import com.brekhin.smartSoft.model.ActivityMonitoringEntity;
import com.brekhin.smartSoft.repository.ActivityMonitoringRepository;
import com.brekhin.smartSoft.repository.projection.IFrequentlyUsedFormsTOProjection;
import com.brekhin.smartSoft.service.ActivityMonitoringService;
import com.brekhin.smartSoft.to.out.ActivityInterruptedTO;
import com.brekhin.smartSoft.to.out.FormsUsedInLastHourTO;
import com.brekhin.smartSoft.util.CSVToEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ActivityMonitoringServiceImpl implements ActivityMonitoringService {

    @Value("${file.name}")
    private String fileName;

    private final ActivityMonitoringRepository activityMonitoringRepository;

    @Autowired
    public ActivityMonitoringServiceImpl(ActivityMonitoringRepository activityMonitoringRepository) {
        this.activityMonitoringRepository = activityMonitoringRepository;
    }

    @Override
    public void saveActivityMonitoringDataToDB() {
        LinkedList<ActivityMonitoringEntity> acmList = CSVToEntityUtil.convertCSVToEntitiesList(fileName);
        activityMonitoringRepository.saveAll(acmList);
    }

    @Override
    public List<IFrequentlyUsedFormsTOProjection> get5FrequentlyUsedForms() {
        List<IFrequentlyUsedFormsTOProjection> frequentlyUsedForms = activityMonitoringRepository.get5FrequentlyUsedForms();
        return frequentlyUsedForms;
    }

    @Override
    @Cacheable("am")
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

            if (Math.abs(hours) * 60 + Math.abs(minutes) + Math.round(Math.abs(seconds) / 60) < 60) {
                result.add(new FormsUsedInLastHourTO(actM.getSsoid(), actM.getFormid()));
            }
        }

        return result;
    }

    @Override
    @Cacheable("acm")
    public PagedListHolder getInterruptedActivities(int page, int size) {

        List<ActivityMonitoringEntity> monitoringList = activityMonitoringRepository.findAllOrderByTs();
        HashMap<String, List<String>> personActivityListStep = new HashMap<>();
        List<ActivityInterruptedTO> interruptedActivities = new ArrayList<>();

        for (ActivityMonitoringEntity am : monitoringList) {
            if (!personActivityListStep.containsKey(am.getSsoid())) {
                personActivityListStep.put(am.getSsoid(), new ArrayList<>());
                personActivityListStep.get(am.getSsoid()).add(am.getSubtype());
            } else {
                personActivityListStep.get(am.getSsoid()).add(am.getSubtype());
            }
        }

        for (String s : personActivityListStep.keySet()) {
            List<String> strings = personActivityListStep.get(s);
            if (!strings.contains("success")) {
                interruptedActivities.add(new ActivityInterruptedTO(s, strings.get(strings.size() - 1)));
            }
        }

        PagedListHolder pageResult = new PagedListHolder(interruptedActivities);
        pageResult.setPage(page);
        pageResult.setPageSize(size);

        return pageResult;
    }
}
