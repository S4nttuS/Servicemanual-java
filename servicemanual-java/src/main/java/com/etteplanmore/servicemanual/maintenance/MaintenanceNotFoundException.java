package com.etteplanmore.servicemanual.maintenance;

class MaintenanceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    MaintenanceNotFoundException(Long id) {
        super("Could not find maintenance job with id " + id);
    }
}