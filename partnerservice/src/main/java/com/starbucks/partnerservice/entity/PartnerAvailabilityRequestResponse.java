package com.starbucks.partnerservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartnerAvailabilityRequestResponse {
	private Integer partnerId;
	 private Date startDate;
	 private Date endDate;
	 List < RequestsEntity> requests = new ArrayList < RequestsEntity> ();
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
	public List<RequestsEntity> getRequests() {
		return requests;
	}
	public void setRequests(List<RequestsEntity> requests) {
		this.requests = requests;
	}
	public PartnerAvailabilityRequestResponse(Integer partnerId, Date startDate, Date endDate,
			List<RequestsEntity> requests) {
		super();
		this.partnerId = partnerId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requests = requests;
	}
	public PartnerAvailabilityRequestResponse() {
		super();
	}
	@Override
	public String toString() {
		return "PartnerAvailabilityRequestResponse [partnerId=" + partnerId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", requests=" + requests + "]";
	}
}
