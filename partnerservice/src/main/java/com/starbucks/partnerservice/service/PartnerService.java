package com.starbucks.partnerservice.service;

import com.starbucks.integration.model.PartnerAvailabilityRequest;
import com.starbucks.integration.model.PartnerAvailability;
import com.starbucks.integration.model.PartnerPreferredHours;
import com.starbucks.partnerservice.entity.PartnerAvailabilityRequestResponse;
import com.starbucks.partnerservice.entity.PartnerAvailabilityResponse;

import java.time.LocalDateTime;
import java.util.Date;

public interface PartnerService {

    public PartnerAvailabilityResponse getPartnerAvailability(Integer partnerId, Date startDate, Date endDate);
    public PartnerAvailabilityRequestResponse getPartnerAvailabilityRequests(Integer partnerId, Integer year,Date startDate, Date endDate);
    public PartnerPreferredHours getPartnerPreferredHours(Integer partnerId);
}
