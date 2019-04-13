package com.brekhin.smartSoft.controller;

import com.brekhin.smartSoft.repository.projection.IFrequentlyUsedFormsTOProjection;
import com.brekhin.smartSoft.service.ActivityMonitoringService;
import com.brekhin.smartSoft.to.out.ActivityInterruptedTO;
import com.brekhin.smartSoft.to.out.FormsUsedInLastHourTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ActivityMonitoringController {
    private final ActivityMonitoringService activityMonitoringService;

    @Autowired
    public ActivityMonitoringController(ActivityMonitoringService activityMonitoringService) {
        this.activityMonitoringService = activityMonitoringService;
        activityMonitoringService.saveActivityMonitoringDataToDB();
    }

    @GetMapping(path = "/topForms")
    public String getTopForms(Model model) {
        List<IFrequentlyUsedFormsTOProjection> frequentlyUsedForms = activityMonitoringService.get5FrequentlyUsedForms();
        model.addAttribute("frequentlyUsedForms", frequentlyUsedForms);
        return "topForms";
    }

    @GetMapping(path = "/activity")
    public String getInterruptedActivities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model) {

        PagedListHolder interruptedActivities = activityMonitoringService
                .getInterruptedActivities(page, size);

        model.addAttribute("url", "/activity");
        model.addAttribute("page", interruptedActivities);

        return "interruptedActivity";
    }

    @GetMapping("/")
    public String getLastAction(Model model) {
        List<FormsUsedInLastHourTO> formsUsedInLastHour = activityMonitoringService
                .getFormsUsedInLastHour();
        model.addAttribute("formsUsedInLastHour", formsUsedInLastHour);
        return "home";
    }

}
