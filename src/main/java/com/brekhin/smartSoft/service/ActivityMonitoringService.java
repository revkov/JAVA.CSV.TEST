package com.brekhin.smartSoft.service;

import com.brekhin.smartSoft.repository.projection.IFrequentlyUsedFormsTOProjection;
import com.brekhin.smartSoft.to.out.FormsUsedInLastHourTO;
import org.springframework.beans.support.PagedListHolder;

import java.util.List;

public interface ActivityMonitoringService {

    void saveActivityMonitoringDataToDB();

    List<IFrequentlyUsedFormsTOProjection> get5FrequentlyUsedForms();

    List<FormsUsedInLastHourTO> getFormsUsedInLastHour();

    PagedListHolder getInterruptedActivities(int page, int size);
}
