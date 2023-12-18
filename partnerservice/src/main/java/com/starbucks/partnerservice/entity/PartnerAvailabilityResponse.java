package com.starbucks.partnerservice.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class PartnerAvailabilityResponse {

    Integer partnerId;

    Date startDate;

    Date endDate;

    Integer numberOfWeeks;

    List<Availability> availabilities;

    public PartnerAvailabilityResponse() {
    }

    public PartnerAvailabilityResponse(Integer partnerId, Date startDate, Date endDate, Integer numberOfWeeks, List<Availability> availabilities) {
        this.partnerId = partnerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfWeeks = numberOfWeeks;
        this.availabilities = availabilities;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getNumberOfWeeks() {
        return numberOfWeeks;
    }

    public void setNumberOfWeeks(Integer numberOfWeeks) {
        this.numberOfWeeks = numberOfWeeks;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}