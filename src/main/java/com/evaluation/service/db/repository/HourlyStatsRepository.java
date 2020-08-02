package com.evaluation.service.db.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.service.db.model.HourlyStats;
import com.evaluation.service.db.model.HourlyStatsId;

@Repository
public interface HourlyStatsRepository extends CrudRepository<HourlyStats, Integer> {

	Optional<HourlyStats> findByHourlyStatsId(HourlyStatsId hourlyStatsId);

	void deleteByHourlyStatsId(HourlyStatsId hourlyStatsId);

}
