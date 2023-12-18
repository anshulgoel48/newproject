package com.starbucks.partnerservice.entity;

import com.starbucks.integration.model.AvailabilityEntity;

import java.util.List;

public class Availability{
    Integer id;

    List<AvailabilityEntity> generalAvailability;

    public Availability() {
    }

    public Availability(Integer id, List<AvailabilityEntity> generalAvailability) {
        this.id = id;
        this.generalAvailability = generalAvailability;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AvailabilityEntity> getGeneralAvailability() {
        return generalAvailability;
    }

    public void setGeneralAvailability(List<AvailabilityEntity> generalAvailability) {
        this.generalAvailability = generalAvailability;
    }
}
