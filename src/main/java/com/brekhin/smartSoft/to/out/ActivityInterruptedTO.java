package com.brekhin.smartSoft.to.out;

public class ActivityInterruptedTO {
    private String activityMonitoring;
    private String step;

    public ActivityInterruptedTO(String activityMonitoring, String step) {
        this.activityMonitoring = activityMonitoring;
        this.step = step;
    }

    public String getActivityMonitoring() {
        return activityMonitoring;
    }

    public String getStep() {
        return step;
    }
}
