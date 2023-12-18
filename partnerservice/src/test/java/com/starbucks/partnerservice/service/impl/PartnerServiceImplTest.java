package com.starbucks.partnerservice.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starbucks.integration.model.PartnerAvailability;
import com.starbucks.integration.model.PartnerAvailabilityRequest;
import com.starbucks.integration.service.impl.WorkforceMgmtServiceImpl;
import com.starbucks.partnerservice.entity.PartnerAvailabilityRequestResponse;
import com.starbucks.partnerservice.entity.PartnerAvailabilityResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class PartnerServiceImplTest {

    @InjectMocks
    private PartnerServiceImpl partnerService;

    @Mock
    private WorkforceMgmtServiceImpl workforceMgmtService;

    @Test
    public void testGetPartnerAvailability_success(){
        PartnerAvailability partnerAvailability = buildPartnerAvailability();
        Mockito.when(workforceMgmtService.getAllAvailabilities(anyInt()))
                .thenReturn(partnerAvailability);
        PartnerAvailabilityResponse response = partnerService.getPartnerAvailability(1616515, Date.from(Instant.ofEpochSecond(1697846400)), Date.from(Instant.ofEpochSecond(1700524800)));
        assertEquals(1616515, response.getPartnerId());
        assertEquals(2, response.getAvailabilities().size());
        assertEquals(2, response.getNumberOfWeeks());

    }
    
    @Test
    public void testGetPartnerAvailabilityRequest_success(){
        PartnerAvailabilityRequest partnerAvailabilityRequest = buildPartnerAvailabilityRequest();
        Mockito.when(workforceMgmtService.getPartnerAvailabilityRequests(anyInt(), anyInt())).thenReturn(partnerAvailabilityRequest);
        PartnerAvailabilityRequestResponse response = partnerService.getPartnerAvailabilityRequests(1616515, 2023, Date.from(Instant.ofEpochSecond(1697846400)), Date.from(Instant.ofEpochSecond(1700524800)));
        //PartnerAvailabilityResponse response = partnerService.getPartnerAvailability(1616515, Date.from(Instant.ofEpochSecond(1697846400)), Date.from(Instant.ofEpochSecond(1700524800)));
        assertEquals(1616515, response.getPartnerId());
        assertEquals(1, response.getRequests().size());

    }

//    @Test
//    public void testGetPartnerAvailability_internalServerError(){
//        Mockito.when(workforceMgmtService.getAllAvailabilities(anyInt()))
//                .thenThrow(NullPointerException.class);
//        PartnerAvailabilityResponse response = partnerService.getPartnerAvailability(1616515, Date.from(Instant.ofEpochSecond(1697846400)), Date.from(Instant.ofEpochSecond(1700524800)));
//    }

    @Test
    public void testGetPartnerAvailability_OutOfRangeDates_emptyResponse(){
        PartnerAvailability partnerAvailability = buildPartnerAvailability();
        Mockito.when(workforceMgmtService.getAllAvailabilities(anyInt()))
                .thenReturn(partnerAvailability);
        PartnerAvailabilityResponse response = partnerService.getPartnerAvailability(1616515, Date.from(Instant.ofEpochSecond(1638230400)), Date.from(Instant.ofEpochSecond(1659139200)));
        assertEquals(0, response.getAvailabilities().size());
        assertEquals(0, response.getNumberOfWeeks());
    }

    @Test
    public void testGetPartnerAvailabilityRequest_OutOfRangeDates_emptyResponse(){
        PartnerAvailabilityRequest partnerAvailabilityRequest = buildPartnerAvailabilityRequest();
        Mockito.when(workforceMgmtService.getPartnerAvailabilityRequests(anyInt(), anyInt())).thenReturn(partnerAvailabilityRequest);
        PartnerAvailabilityRequestResponse response = partnerService.getPartnerAvailabilityRequests(1616515, 2023, Date.from(Instant.ofEpochSecond(1638230400)), Date.from(Instant.ofEpochSecond(1659139200)));
       // PartnerAvailabilityRequestResponse response = partnerService.getPartnerAvailabilityRequest(1616515, Date.from(Instant.ofEpochSecond(1638230400)), Date.from(Instant.ofEpochSecond(1659139200)));
        assertEquals(0, response.getRequests().size());
    }
//    @Test
//    public void testGetPartnerAvailabilityIn(){
//        PartnerAvailability partnerAvailability = buildPartnerAvailability();
//        Mockito.when(workforceMgmtService.getAllAvailabilities(anyInt()))
//                .thenReturn(partnerAvailability);
//        PartnerAvailabilityResponse response = partnerService.getPartnerAvailability(1616515, Date.from(Instant.ofEpochSecond(1697846400)), Date.from(Instant.ofEpochSecond(1700524800)));
//        assertEquals(response, partnerAvailability);
//    }

    private PartnerAvailability buildPartnerAvailability(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        try{
            FileReader fileReader = new FileReader(ResourceUtils.getFile("classpath:test/availability_response.json"));
            return gson.fromJson(fileReader, PartnerAvailability.class);
        } catch(Exception e){
            log.error("Error while parsing json file");
            return null;
        }
    }

    private PartnerAvailabilityRequest buildPartnerAvailabilityRequest(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
        try{
            FileReader fileReader = new FileReader(ResourceUtils.getFile("classpath:test/availability_requests_by_year.json"));
            return gson.fromJson(fileReader, PartnerAvailabilityRequest.class);
        } catch(Exception e){
            log.error("Error while parsing json file");
            return null;
        }
    }

}
