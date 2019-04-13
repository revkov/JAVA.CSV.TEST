package com.brekhin.smartSoft.to.out;

public class FormsUsedInLastHourTO {
    private final String userId;
    private final String formId;

    public FormsUsedInLastHourTO(String userId, String formId) {
        this.userId = userId;
        this.formId = formId;
    }

    public String getUserId() {
        return userId;
    }

    public String getFormId() {
        return formId;
    }
}
