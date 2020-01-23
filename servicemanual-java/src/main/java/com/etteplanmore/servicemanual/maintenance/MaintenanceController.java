package com.etteplanmore.servicemanual.maintenance;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.etteplanmore.servicemanual.factorydevice.FactoryDeviceController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MaintenanceController {

    private final FactoryDeviceController factoryDeviceController;

    private final MaintenanceRepository repository;

    MaintenanceController(MaintenanceRepository repository, FactoryDeviceController factoryDeviceController) {
        this.repository = repository;
        this.factoryDeviceController = factoryDeviceController;
    }

    @GetMapping("/maintenances")
    List<Maintenance> all() {
        return repository.findAllByOrderByCriticalityDescEntryDateDesc();
    }

    @GetMapping("/maintenances/findbydevice/{deviceId}")
    List<Maintenance> findByDevice(@PathVariable Long deviceId) {
        if(factoryDeviceController.one(deviceId) != null)
            return repository.findByDeviceIdOrderByCriticalityDescEntryDateDesc(deviceId);
        return null;
    }

    @GetMapping("/maintenances/{id}")
    Maintenance one(@PathVariable Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new MaintenanceNotFoundException(id));
    }
    
    @PostMapping("/maintenances/add")
    Maintenance add(@RequestBody Maintenance maintenance){
        if(factoryDeviceController.one(maintenance.getDeviceId()) != null)
            return repository.save(maintenance);
        return null;
    }

    @PutMapping("/maintenances/edit")
    Maintenance edit(@RequestBody Maintenance maintenance){
        Maintenance editable = one(maintenance.getId());
        if(editable != null && factoryDeviceController.one(maintenance.getDeviceId()) != null){
            return repository.save(maintenance);
        }
        return null;
    }

    @DeleteMapping("/maintenances/delete/{id}")
    Maintenance delete(@PathVariable Long id){
        Maintenance deletable = one(id);
        if(deletable != null){
            repository.delete(deletable);
        }
        return deletable;
    }
}