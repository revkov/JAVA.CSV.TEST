package com.brekhin.smartSoft.service;

import com.brekhin.smartSoft.model.ActivityMonitoring;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface ActivityMonitoringService {

    void saveActivityMonitoringDataToDB();

    Map<String, BigInteger> get5FrequentlyUsedForms();

    Map<String, String>  getActivity();
}
