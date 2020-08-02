package com.evaluation.service.controller.model;

import java.sql.Time;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputObject {
	
	@JsonProperty
	private Integer customerID;
	
	@JsonProperty
	private Integer tagID;
	
	@JsonProperty
	private String userID;
	
	@JsonProperty
	private Long remoteIP;
	
	@JsonProperty
	private LocalDateTime time;
	
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getTagID() {
		return tagID;
	}
	public void setTagID(Integer tagID) {
		this.tagID = tagID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Long getRemoteIP() {
		return remoteIP;
	}
	public void setRemoteIP(Long remoteIP) {
		this.remoteIP = remoteIP;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime times) {
		this.time = times;
	}
}
