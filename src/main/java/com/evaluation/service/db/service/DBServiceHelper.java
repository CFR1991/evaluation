package com.evaluation.service.db.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.model.HourlyStats;
import com.evaluation.service.db.model.HourlyStatsId;

@Service
class DBServiceHelper {

	 HourlyStatsId getHourlyStatsId(InputObject inputObject) {
		String year_day_hour = getTimeString_year_day_hour(inputObject.getTimestamp());
		return createAndGetHourlyStatsId(inputObject.getCustomerID(), year_day_hour);
	}

	private HourlyStatsId createAndGetHourlyStatsId(Integer customerId, String year_day_hour) {
		HourlyStatsId hourlyStatsId = new HourlyStatsId();
		hourlyStatsId.setCustomerId(customerId);
		hourlyStatsId.setYear_day_hour(year_day_hour);
		return hourlyStatsId;
	}

	private String getTimeString_year_day_hour(Long timestamp) {
		Timestamp ts = new Timestamp(timestamp);
		LocalDateTime ldt = ts.toLocalDateTime();
		return String.format("%d_%d_%d", ldt.getYear(), ldt.getDayOfYear(), ldt.getHour());
	}

	//

	public HourlyStats updateAndGetHourlyStats(HourlyStats hourlyStats, boolean b) {
		return updateRequestsAndInvalidRequests(hourlyStats, b);
	}

	public HourlyStats createAndGetHourlyStats(HourlyStatsId hourlyStatsId, boolean b) {
		HourlyStats hourlyStats = new HourlyStats();
		hourlyStats.setHourlyStatsId(hourlyStatsId);
		return updateRequestsAndInvalidRequests(hourlyStats, b);
	}

	private HourlyStats updateRequestsAndInvalidRequests(HourlyStats hourlyStats, boolean b) {
		hourlyStats.setRequest_count(hourlyStats.getRequest_count() + 1L);
		if (!b) {
			hourlyStats.setInvalid_count(hourlyStats.getInvalid_count() + 1L);
		}
		return hourlyStats;
	}

}
