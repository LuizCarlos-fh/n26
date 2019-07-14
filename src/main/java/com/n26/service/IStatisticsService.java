package com.n26.service;

import com.n26.domain.StatisticsResponse;

/**
 * The Interface IStatisticsService.
 */
public interface IStatisticsService {

	/**
	 * Fetch statistics.
	 *
	 * @return the statistics result
	 */
	public StatisticsResponse fetchStatistics();
}
