package com.starbucks.partnerservice.entity;

import java.util.Date;

public class RequestsEntity {
	private Integer id;
	 private String status;
	 private String comments;
	 private Date effectiveFrom;
	 private Date endsAfter;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getEffectiveFrom() {
		return effectiveFrom;
	}
	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}
	public Date getEndsAfter() {
		return endsAfter;
	}
	public void setEndsAfter(Date endsAfter) {
		this.endsAfter = endsAfter;
	}
	public RequestsEntity(int id, String status, String comments, Date effectiveFrom, Date endsAfter) {
		super();
		this.id = id;
		this.status = status;
		this.comments = comments;
		this.effectiveFrom = effectiveFrom;
		this.endsAfter = endsAfter;
	}
	public RequestsEntity() {
		super();
	}
	@Override
	public String toString() {
		return "RequestsEntity [id=" + id + ", status=" + status + ", comments=" + comments + ", effectiveFrom="
				+ effectiveFrom + ", endsAfter=" + endsAfter + "]";
	}
}
