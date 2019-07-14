package com.n26.repository;

import java.util.List;

import com.n26.domain.Transaction;

/**
 * The Interface IStatisticsRepository.
 */
public interface IStatisticsRepository {

	/**
	 * Fetches the statistics.
	 *
	 * @param currentTimestamp the current timestamp
	 * @param sixtySecondsAgo the sixty seconds ago
	 * @return the list of transactions
	 */
	public List<Transaction> fetchStatistics(Long currentTimestamp, Long sixtySecondsAgo);
}
