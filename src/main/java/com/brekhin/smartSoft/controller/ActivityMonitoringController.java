package com.brekhin.smartSoft.controller;

import com.brekhin.smartSoft.service.ActivityMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActivityMonitoringController {
    private ActivityMonitoringService activityMonitoringService;

    @Autowired
    public ActivityMonitoringController(ActivityMonitoringService activityMonitoringService) {
        this.activityMonitoringService = activityMonitoringService;
    }

    @GetMapping(path = "/")
    public String loadData(){
        activityMonitoringService.saveActivityMonitoringDataToDB();
        return "OK!";
    }
}
