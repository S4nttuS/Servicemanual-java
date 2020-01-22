package com.etteplanmore.servicemanual.maintenance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Maintenance {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long deviceId;
    private Date entryDate;
    private String description;
    private int status;

    protected Maintenance() {}

    /**
     * Constructor of Maintenance class
     * 
     * @param deviceId Id of the factory device
     * @param entryDate Date for the maintenance entry
     * @param description Description for the maintenance job
     * @param status Status of the maintenance job (0-1)
     */
    public Maintenance(Long deviceId, Date entryDate, String description, int status) {
        this.deviceId = deviceId;
        this.entryDate = entryDate;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public Long getDeviceId() {
        return this.deviceId;
    }

    public Date getEntryDate() {
        return this.entryDate;
    }

    public String getDescription() {
        return this.description;
    }

    public int getStatus() {
        return this.status;
    }
}