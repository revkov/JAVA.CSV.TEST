package com.brekhin.smartSoft.repository;

import com.brekhin.smartSoft.model.ActivityMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ActivityMonitoringRepository extends JpaRepository<ActivityMonitoring, UUID> {
}
