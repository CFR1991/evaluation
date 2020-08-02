package com.evaluation.service.db;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.evaluation.service.db.model.HourlyStats;
import com.evaluation.service.db.model.HourlyStatsId;

@Service
class DBServiceHelper {

	public HourlyStatsId getHourlyStatsId(Integer customerId) {
		String year_day_hour = getTimeString_year_day_hour();
		return new HourlyStatsId(customerId, year_day_hour);
	}

	private String getTimeString_year_day_hour() {
		LocalDateTime ldt = LocalDateTime.now();
		return String.format("%d_%d_%d", ldt.getYear(), ldt.getDayOfYear(), ldt.getHour());
	}

	//

	public HourlyStats updateAndGetHourlyStats(HourlyStats hourlyStats, boolean b) {
		hourlyStats = updateRequestsAndInvalidRequests(hourlyStats, b);
		return hourlyStats;
	}

	public HourlyStats createAndGetHourlyStats(HourlyStatsId hourlyStatsId, boolean b) {
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
