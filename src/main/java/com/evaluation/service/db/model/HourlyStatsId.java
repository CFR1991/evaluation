package com.evaluation.service.db.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HourlyStatsId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getYear_day_hour() {
		return year_day_hour;
	}

	public void setYear_day_hour(String year_day_hour) {
		this.year_day_hour = year_day_hour;
	}

	@Column(name = "customerId", length = 11, nullable = false)
	private Integer customerId;

	@Column(name = "year_day_hour", length = 20, nullable = false)
	private String year_day_hour;

	public HourlyStatsId(Integer customerId, String year_day_hour) {
		this.customerId = customerId;
		this.year_day_hour = year_day_hour;
	}
}
