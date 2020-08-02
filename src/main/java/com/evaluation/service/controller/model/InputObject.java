package com.evaluation.service.controller.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputObject {

	@JsonProperty
	private Integer customerId;

	@JsonProperty
	private Integer tagId;

	@JsonProperty
	private String userId;

	@JsonProperty
	private Long remoteIP;

	@JsonProperty
	private LocalDateTime time;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
