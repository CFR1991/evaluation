package com.evaluation.service.db;

import java.time.LocalDateTime;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.model.HourlyStats;
import com.evaluation.service.db.model.HourlyStatsId;

class DBServiceHelper {

	String getTimeString_year_day_hour() {
		LocalDateTime ldt = LocalDateTime.now();
		return String.format("%d_%d_%d", ldt.getYear(), ldt.getDayOfYear(), ldt.getHour());
	}

	public HourlyStats updateAndGetHourlyStats(HourlyStats hourlyStats, boolean b) {
		hourlyStats = updateRequestsAndInvalidRequests(hourlyStats, b);
		return hourlyStats;
	}

	public HourlyStats createAndGetHourlyStats(InputObject input, HourlyStatsId hourlyStatsId, boolean b) {
		HourlyStats hourlyStats = new HourlyStats(hourlyStatsId);
		hourlyStats = updateRequestsAndInvalidRequests(hourlyStats, b);
		return hourlyStats;
	}

	private HourlyStats updateRequestsAndInvalidRequests(HourlyStats hourlyStats, boolean b) {
		hourlyStats.setRequest_count(hourlyStats.getRequest_count() + 1L);
		if (!b) {
			hourlyStats.setInvalid_count(hourlyStats.getInvalid_count() + 1L);
		}
		return hourlyStats;
	}

}
