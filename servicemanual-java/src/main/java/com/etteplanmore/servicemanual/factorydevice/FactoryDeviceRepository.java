package com.etteplanmore.servicemanual.factorydevice;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactoryDeviceRepository extends JpaRepository<FactoryDevice, Long> {
    Page<FactoryDevice> findAll(Pageable pageable);
}