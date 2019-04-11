package com.brekhin.smartSoft.service;

import java.math.BigInteger;
import java.util.Map;

public interface ActivityMonitoringService {

    void saveActivityMonitoringDataToDB();

    Map<String, BigInteger> get5FrequentlyUsedForms();
}
