package com.evaluation.service.db.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hourlyStats")
public class HourlyStats {

	@EmbeddedId
	private HourlyStatsId hourlyStatsId;

	@Column(name = "request_count", length = 20, nullable = false)
	private Long request_count = 0L;

	@Column(name = "invalid_count", length = 20, nullable = false)
	private Long invalid_count = 0L;

	public HourlyStatsId getHourlyStatsId() {
		return this.hourlyStatsId;
	}

	public void setHourlyStatsId(HourlyStatsId hourlyStatsId) {
		this.hourlyStatsId = hourlyStatsId;
	}

	public Long getRequest_count() {
		return request_count;
	}

	public void setRequest_count(Long request_count) {
		this.request_count = request_count;
	}

	public Long getInvalid_count() {
		return invalid_count;
	}

	public void setInvalid_count(Long invalid_count) {
		this.invalid_count = invalid_count;
	}

}
