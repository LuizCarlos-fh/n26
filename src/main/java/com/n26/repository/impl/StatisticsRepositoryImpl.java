package com.n26.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.n26.domain.Transaction;
import com.n26.repository.IStatisticsRepository;

/**
 * The Class StatisticsRepositoryImpl.
 */
@Repository
public class StatisticsRepositoryImpl implements IStatisticsRepository {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.repository.IStatisticsRepository#fetchStatistics(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Transaction> fetchStatistics(Long currentTimestamp, Long sixtySecondsAgo) {

		String sql = "SELECT * FROM transaction WHERE timestamp <=" + currentTimestamp + " AND timestamp >=" + sixtySecondsAgo + ";";

		List<Transaction> transactionList = jdbcTemplate.query(sql, new RowMapper<Transaction>() {
			public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
				Transaction transaction = new Transaction();
				transaction.setAmount(rs.getBigDecimal("amount"));
				transaction.setTimestamp(rs.getLong("timestamp"));
				return transaction;
			}
		});

		return transactionList;
	}

}
