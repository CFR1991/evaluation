package com.evaluation.service.db;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.service.controller.model.InputObject;
import com.evaluation.service.db.model.HourlyStats;
import com.evaluation.service.db.model.HourlyStatsId;
import com.evaluation.service.db.repository.CustomerRepository;
import com.evaluation.service.db.repository.HourlyStatsRepository;
import com.evaluation.service.db.repository.IpBlacklistRepository;
import com.evaluation.service.db.repository.UaBlacklistRepository;

@Service
public class DBService {

	private final CustomerRepository customerRepository;
	private final IpBlacklistRepository ipBlacklistRepository;
	private final UaBlacklistRepository uaBlacklistRepository;
	private final HourlyStatsRepository hourlyStatsRepository;
	private final DBServiceHelper helper = new DBServiceHelper();

	@Autowired
	public DBService(CustomerRepository customerRepository, IpBlacklistRepository ipBlacklistRepository,
			UaBlacklistRepository uaBlacklistRepository, HourlyStatsRepository hourlyStatsRepository) {
		this.customerRepository = customerRepository;
		this.ipBlacklistRepository = ipBlacklistRepository;
		this.uaBlacklistRepository = uaBlacklistRepository;
		this.hourlyStatsRepository = hourlyStatsRepository;
	}

	public boolean customerIDisInDataBaseAndNotDisabled(Integer coustumerID) {
		return customerRepository.findById(coustumerID) != null
				&& customerRepository.findById(coustumerID).get().isActive();
	}

	public boolean remoteIPisNotInBlackList(Long string) {
		return ipBlacklistRepository.findById(string) == null;
	}

	public boolean userIDisNotInBlackList(String ua) {
		return uaBlacklistRepository.findById(ua) == null;
	}

	public void addInput2DB(InputObject input, boolean b) {
		String year_day_hour = helper.getTimeString_year_day_hour();
		addInput2DBIntern(input, year_day_hour, b);
	}

	private void addInput2DBIntern(InputObject input, String year_day_hour, boolean b) {
		HourlyStatsId hourlyStatsId = new HourlyStatsId(input.getCustomerID(), year_day_hour);
		Optional<HourlyStats> hourlyStats = hourlyStatsRepository.findByHourlyStatsId(hourlyStatsId);
		if (hourlyStats.isPresent()) {
			hourlyStatsRepository.deleteByHourlyStatsId(hourlyStatsId);
			hourlyStatsRepository.save(helper.updateAndGetHourlyStats(hourlyStats.get(), b));
			return;
		}
		hourlyStatsRepository.save(helper.createAndGetHourlyStats(input, hourlyStatsId, b));
		return;
	}

}
