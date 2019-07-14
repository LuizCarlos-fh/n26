package com.n26.repository;

import java.math.BigDecimal;

/**
 * The Interface ITransactionsRepository.
 */
public interface ITransactionsRepository {

	/**
	 * Saves a transaction.
	 *
	 * @param amount the amount
	 * @param timestamp the timestamp
	 */
	public void saveTransaction(BigDecimal amount, Long timestamp);

	/**
	 * Deletes all transactions.
	 */
	public void deleteAllTransactions();
}
