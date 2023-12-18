package com.starbucks.partnerservice.controller;

import com.starbucks.partnerservice.entity.PartnerAvailabilityRequestResponse;
import com.starbucks.partnerservice.entity.PartnerAvailabilityResponse;
import com.starbucks.partnerservice.service.impl.PartnerServiceImpl;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PartnerController.class)
@ContextConfiguration(locations = "/test/test-config.xml", classes = PartnerController.class)
public class PartnerControllerTest {

	@Rule
	private ExpectedException exception = ExpectedException.none();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PartnerServiceImpl partnerService;

	@Test
	public void getPartnerAvailability_success() throws Exception {

		PartnerAvailabilityResponse response = new PartnerAvailabilityResponse();
		Mockito.when(partnerService.getPartnerAvailability(anyInt(), any(Date.class), any(Date.class)))
				.thenReturn(response);
		mockMvc.perform(get("/partner/availability?id=1234&start_date=2022-10-31T00:00:00&end_date=2022-11-30T00:00:00"))
				.andExpect(status().isOk());
	}

	@Test
	public void getAllRecordsForAvailabilityRequests_success() throws Exception {
		PartnerAvailabilityRequestResponse partnerAvailabilityRequestResponse = new PartnerAvailabilityRequestResponse();
		Mockito.when(partnerService.getPartnerAvailabilityRequests(anyInt(),anyInt(), any(Date.class), any(Date.class)))
		.thenReturn(partnerAvailabilityRequestResponse);
		mockMvc.perform(get("/partner/availability-requests?id=1616515&year=2023&start_date=2023-08-12T00:00:00&end_date=2023-11-25T00:00:00"))
		.andExpect(status().isOk());
		
		
		
	}
//	@Test
//	public void getPartnerAvailability_internalServerError() throws Exception {
//
//		PartnerAvailabilityResponse response = new PartnerAvailabilityResponse();
//		exception.expect(NullPointerException.class);
//		Mockito.when(partnerService.getPartnerAvailability(anyInt(), any(Date.class), any(Date.class)))
//				.thenThrow(NullPointerException.class);
//		mockMvc.perform(get("/partner/availability?id=1234&start_date=2022-10-31T00:00:00&end_date=2022-11-30T00:00:00"))
//				.andExpect(status().isInternalServerError());
//	}

	@Test
	public void getPartnerAvailabilityNullId_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability?start_date=2022-10-31T00:00:00&end_date=2022-11-30T00:00:00"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getPartnerAvailabilityRequestNullId_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability-requests?year=2023&start_date=2023-08-12T00:00:00&end_date=2023-11-25T00:00:00"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getPartnerAvailabilityInvalidId_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability?start_date=2022-10-31T00:00:00&end_date=2022-11-30T00:00:00"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getPartnerAvailabilityNullDate_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability?id=1234&end_date=2022-11-30T00:00:00"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getPartnerAvailabilityRequestNullDate_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability-requests?id=1616515&year=2023&end_date=2023-11-25T00:00:00"))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getPartnerAvailabilityInvalidDate_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability?id=1234&start_date=2022-10-31T00:00:00&end_date=2022-11-"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void getPartnerAvailabilityRequestInvalidDate_badRequest() throws Exception {
		mockMvc.perform(get("/partner/availability-requests?id=1616515&year=2023&start_date=2022-10-31T00:00:00&end_date=2022-11-"))
				.andExpect(status().isBadRequest());
	}

}
