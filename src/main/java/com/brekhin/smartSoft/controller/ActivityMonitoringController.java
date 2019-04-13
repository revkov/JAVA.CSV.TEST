package com.brekhin.smartSoft.controller;

import com.brekhin.smartSoft.service.ActivityMonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

@RestController
public class ActivityMonitoringController {
    private final ActivityMonitoringService activityMonitoringService;

    @Autowired
    public ActivityMonitoringController(ActivityMonitoringService activityMonitoringService) {
        this.activityMonitoringService = activityMonitoringService;
    }

    @GetMapping(path = "/")
    public String loadData() {
        activityMonitoringService.saveActivityMonitoringDataToDB();
        return "OK!";
    }

    @GetMapping(path = "/topForms")
    public String getTopForms(Model model){
        Map<String, BigInteger> frequentlyUsedForms = activityMonitoringService.get5FrequentlyUsedForms();
        return "OK";
    }

    @GetMapping(path = "/activity")
    public String getActivity(){
        activityMonitoringService.getActivity();
        return "OK";
    }

    @GetMapping(path="/lastAction")
    public String getLastAction(){
        activityMonitoringService.getFormsUsedInLastHour();
        return "OK!";
    }

}
