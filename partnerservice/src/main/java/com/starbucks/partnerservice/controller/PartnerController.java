package com.starbucks.partnerservice.controller;

import com.starbucks.integration.model.PartnerAvailabilityRequest;
import com.starbucks.integration.model.PartnerAvailability;
import com.starbucks.integration.model.PartnerPreferredHours;
import com.starbucks.partnerservice.entity.PartnerAvailabilityRequestResponse;
import com.starbucks.partnerservice.entity.PartnerAvailabilityResponse;
import com.starbucks.partnerservice.service.impl.PartnerServiceImpl;
import jakarta.validation.constraints.NotNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping(value = "/partner")
@Log4j2
public class PartnerController {

    @Autowired
    PartnerServiceImpl employeeService;

    @GetMapping(value = "/availability", produces = "application/json")
    PartnerAvailabilityResponse getPartnerAvailability(@RequestParam(value = "id") @NotNull Integer partnerId, @RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam(value = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        return employeeService.getPartnerAvailability(partnerId, startDate, endDate);
    }

    @GetMapping(value = "/availability-requests", produces = "application/json")
    PartnerAvailabilityRequestResponse getPartnerAvailabilityRequests(@RequestParam(value = "id") @NotNull Integer partnerId, @RequestParam(value = "year") @NotNull Integer year,@RequestParam(value = "start_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam(value = "end_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        return employeeService.getPartnerAvailabilityRequests(partnerId, year, startDate, endDate);
    }

    @GetMapping(value = "/preferred-hours", produces = "application/json")
    PartnerPreferredHours getPartnerPreferredHours(@RequestParam(value = "id") @NotNull Integer partnerId){
        return employeeService.getPartnerPreferredHours(partnerId);
    }

}
