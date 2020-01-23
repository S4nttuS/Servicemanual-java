package com.etteplanmore.servicemanual.maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
    List<Maintenance> findAllByOrderByCriticalityDescEntryDateDesc();
    List<Maintenance> findByDeviceIdOrderByCriticalityDescEntryDateDesc(Long deviceId);
}