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

	@Column(name = "customerId", length = 11, nullable = false)
	private Integer customerId;

	@Column(name = "year_day_hour", length = 20, nullable = false)
	private String year_day_hour;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getYear_day_hour() {
		return this.year_day_hour;
	}

	public void setYear_day_hour(String year_day_hour) {
		this.year_day_hour = year_day_hour;
	}

	@Override
	public boolean equals(Object obj) {
		HourlyStatsId other = (HourlyStatsId) obj;
		if (obj == null) {
			return false;
		}
		if (customerId != other.getCustomerId()) {
			return false;
		}
		return year_day_hour.equals(year_day_hour);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
