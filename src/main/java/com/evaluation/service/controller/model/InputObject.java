package com.evaluation.service.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputObject {

	@JsonProperty
	private Integer customerID;

	@JsonProperty
	private Integer tagID;

	@JsonProperty
	private String userID;

	@JsonProperty
	private String remoteIP;

	@JsonProperty
	private Long timestamp;

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

	public String getRemoteIP() {
		return remoteIP;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
