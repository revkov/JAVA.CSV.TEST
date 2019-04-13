package com.brekhin.smartSoft.repository;

import com.brekhin.smartSoft.model.ActivityMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityMonitoringRepository extends JpaRepository<ActivityMonitoring, UUID> {

    @Query(value = "select formid, count(formid) " +
            "from activity_monitoring " +
            "group by formid " +
            "having formid <> '' " +
            "order by count(formid) " +
            "desc limit 5",
            nativeQuery = true)
    List<List<Object>> get5FrequentlyUsedForms();

    @Query(value = "select *\n" +
            "from activity_monitoring\n" +
            "order by ts asc", nativeQuery = true)
    List<ActivityMonitoring> findAllOrderByTs();
}
