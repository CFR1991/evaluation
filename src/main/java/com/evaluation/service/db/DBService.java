package com.evaluation.service.db;

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
	private final DBServiceHelper helper;

	@Autowired
	public DBService(CustomerRepository customerRepository, IpBlacklistRepository ipBlacklistRepository,
			UaBlacklistRepository uaBlacklistRepository, HourlyStatsRepository hourlyStatsRepository,
			DBServiceHelper helper) {
		this.customerRepository = customerRepository;
		this.ipBlacklistRepository = ipBlacklistRepository;
		this.uaBlacklistRepository = uaBlacklistRepository;
		this.hourlyStatsRepository = hourlyStatsRepository;
		this.helper = helper;
	}

	public boolean customerIdisInDataBaseAndNotDisabled(Integer coustumerID) {
		return customerRepository.findById(coustumerID).isPresent()
				&& customerRepository.findById(coustumerID).get().isActive();
	}

	public boolean remoteIpisNotInIpBlackList(String ip) {
		return !ipBlacklistRepository.findById(ip).isPresent();
	}

	public boolean userIDisNotInUaBlackList(String ua) {
		return !uaBlacklistRepository.findById(ua).isPresent();
	}

	public void addInput2DB(InputObject input, boolean b) {
		addInput2DBIntern(helper.getHourlyStatsId(input), b);
	}

	private void addInput2DBIntern(HourlyStatsId hourlyStatsId, boolean b) {
		Optional<HourlyStats> hourlyStats = hourlyStatsRepository.findByHourlyStatsId(hourlyStatsId);
		if (hourlyStats.isPresent()) {
			hourlyStatsRepository.save(helper.updateAndGetHourlyStats(hourlyStats.get(), b));
			return;
		}
		hourlyStatsRepository.save(helper.createAndGetHourlyStats(hourlyStatsId, b));
	}

}
