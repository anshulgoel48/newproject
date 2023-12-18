package com.starbucks.partnerservice.service.impl;

import com.starbucks.integration.model.*;
import com.starbucks.integration.service.impl.WorkforceMgmtServiceImpl;
import com.starbucks.partnerservice.entity.Availability;
import com.starbucks.partnerservice.entity.PartnerAvailabilityRequestResponse;
import com.starbucks.partnerservice.entity.PartnerAvailabilityResponse;
import com.starbucks.partnerservice.entity.RequestsEntity;
import com.starbucks.partnerservice.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {

	private static final Date MAX_TIME = Date.from(Instant.ofEpochSecond(1988150399)); 
    
	@Autowired
    WorkforceMgmtServiceImpl workforceMgmtServiceImpl;

    public PartnerAvailabilityResponse getPartnerAvailability(Integer partnerId, Date startDate, Date endDate){
     
        PartnerAvailability partnerAvailability = workforceMgmtServiceImpl.getAllAvailabilities(partnerId);
        return buildPartnerAvailabilityResponse(partnerAvailability, startDate,endDate);
    }

    @Override
    public PartnerAvailabilityRequestResponse getPartnerAvailabilityRequests(Integer partnerId, Integer year, Date startDate, Date endDate){
		/*
		 * DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss:SSS'Z'"); LocalDateTime
		 * start_Date = LocalDateTime.parse(startDate,formatter); LocalDateTime end_Date
		 * = LocalDateTime.parse(endDate,formatter);
		 */
    	
        PartnerAvailabilityRequest partnerAvailabilityRequest = workforceMgmtServiceImpl.getPartnerAvailabilityRequests(partnerId, year);
        return buildPartnerAvailabilityRequestResponse(partnerAvailabilityRequest,startDate,endDate);
    }

    public PartnerPreferredHours getPartnerPreferredHours(Integer partnerId){

        return workforceMgmtServiceImpl.getPartnerPreferredHours(partnerId);
    }

    private PartnerAvailabilityResponse buildPartnerAvailabilityResponse(PartnerAvailability partnerAvailability, Date startDate, Date endDate) {
        PartnerAvailabilityResponse response = new PartnerAvailabilityResponse();
        List<Availability> availabilities = new ArrayList<>();

        response.setPartnerId(partnerAvailability.getEmployeeId());
        for (Entity entity : partnerAvailability.getEntities()) {
            Date effectiveFrom = entity.getEffectiveFrom();
            Date endsAfter = entity.getEndsAfter() == null ? MAX_TIME : entity.getEndsAfter();
            /*
            Here, we are checking
            1. if the whole availability period lies inside the dates we want to check for.
            2. if either of input dates lies inside the availability startDate and endDate.
            If either of these condition is true we will pick that availability period.
             */
            if (startDate.before(effectiveFrom) && endDate.after(endsAfter) ||
                    (startDate.after(effectiveFrom) && startDate.before(endsAfter)) ||
                    (endDate.after(effectiveFrom) && endDate.before(endsAfter))) {
                Availability availability = new Availability();
                availability.setId(entity.getId());
                availability.setGeneralAvailability(entity.getGeneralAvailability());
                availabilities.add(availability);
            }
        }
        response.setAvailabilities(availabilities);
        response.setNumberOfWeeks(availabilities.size());
        response.setStartDate(startDate);
        response.setEndDate(endDate);

        return response;
    }
    private PartnerAvailabilityRequestResponse buildPartnerAvailabilityRequestResponse(PartnerAvailabilityRequest partnerAvailabilityRequest, Date startDate, Date endDate){
    	PartnerAvailabilityRequestResponse response = new PartnerAvailabilityRequestResponse();
        ArrayList<RequestsEntity> availabilities = new ArrayList<>();

        response.setPartnerId(partnerAvailabilityRequest.getEmployeeId());
        for(RequestEntity entity: partnerAvailabilityRequest.getEntities()){
        	Date effectiveFrom = entity.getEffectiveFrom();
        	Date endsAfter = entity.getEndsAfter() == null ? MAX_TIME : entity.getEndsAfter();
            if (startDate.before(effectiveFrom) && endDate.after(endsAfter) ||
                    (startDate.after(effectiveFrom) && startDate.before(endsAfter)) ||
                    (endDate.after(effectiveFrom) && endDate.before(endsAfter))) {
        	RequestsEntity requestsentity = new RequestsEntity();
        	requestsentity.setId(entity.getId());
        	requestsentity.setComments(entity.getComments());
        	requestsentity.setEffectiveFrom(entity.getEffectiveFrom());
        	requestsentity.setEndsAfter(entity.getEndsAfter());
        	requestsentity.setStatus(entity.getStatus());
            availabilities.add(requestsentity);
        }
        response.setRequests(availabilities);
        response.setStartDate(startDate);
        response.setEndDate(endDate);
        }
        return response;
    }
}
