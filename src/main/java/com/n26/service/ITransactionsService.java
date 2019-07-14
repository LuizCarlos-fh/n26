package com.n26.service;

import org.springframework.http.ResponseEntity;

import com.n26.domain.TransactionRequest;

/**
 * The Interface ITransactionsService.
 */
public interface ITransactionsService {

	/**
	 * Adds the transaction.
	 *
	 * @param transaction the transaction
	 * @return the response entity
	 */
	public ResponseEntity<String> addTransaction(TransactionRequest transaction);

	/**
	 * Delete all transactions.
	 */
	public void deleteAllTransactions();
}
