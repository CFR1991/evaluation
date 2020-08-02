package com.evaluation.service.db.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "hourly_stats")
public class HourlyStats {

	@EmbeddedId
	private HourlyStatsId hourlyStatsId;

	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "request_count", length = 20, columnDefinition = "Long default 0", nullable = false)
	private Long request_count;

	@Column(name = "invalid_count", length = 20, columnDefinition = "Long default 0", nullable = false)
	private Long invalid_count;

	public HourlyStats(HourlyStatsId hourlyStatsId) {
		this.hourlyStatsId = hourlyStatsId;
	}

	public HourlyStatsId getHourlyStatsId() {
		return hourlyStatsId;
	}

	public void setHourlyStatsId(HourlyStatsId unique_customer_time) {
		this.hourlyStatsId = unique_customer_time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// public Integer getCustomerId() {
	// return this.unique_customer_time.getCustomerId();
	// }
	//
	// public void setCustomerId(Integer customerId) {
	// this.unique_customer_time.setCustomerId(customerId);
	// }

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
