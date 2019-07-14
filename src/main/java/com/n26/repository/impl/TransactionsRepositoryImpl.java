package com.n26.repository.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.n26.repository.ITransactionsRepository;

/**
 * The Class TransactionsRepositoryImpl.
 */
@Repository
public class TransactionsRepositoryImpl implements ITransactionsRepository {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.repository.ITransactionsRepository#saveTransaction(java.math.BigDecimal, java.lang.Long)
	 */
	@Override
	public void saveTransaction(BigDecimal amount, Long timestamp) {

		String sql = "INSERT INTO transaction (amount, timestamp) VALUES (" + amount + "," + timestamp + ");";

		jdbcTemplate.execute(sql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.repository.ITransactionsRepository#deleteAllTransactions()
	 */
	@Override
	public void deleteAllTransactions() {

		String sql = "DELETE transaction;";

		jdbcTemplate.execute(sql);
	}
}
