package com.n26.repository.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.repository.ITransactionsRepository;

/**
 * The Class TransactionsRepositoryImplTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionsRepositoryImplTest {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ITransactionsRepository transactionsRepository;
	
	/**
	 * Save transaction test.
	 */
	@Test
	public void saveTransactionTest() {

		// Arrange
		BigDecimal amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		Long timestamp = Long.valueOf("1538950980000");

		// Act
		transactionsRepository.saveTransaction(amount, timestamp);
		
		String sql = "SELECT COUNT(*) FROM transaction;";
		int count = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);

		// Assert
		Assert.assertEquals(1, count);
	}

	/**
	 * Test delete all transactions.
	 */
	@Test
	public void testDeleteAllTransactions() {

		// Arrange
		BigDecimal amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		Long timestamp = Long.valueOf("1538950980000");
		transactionsRepository.saveTransaction(amount, timestamp);

		// Act
		transactionsRepository.deleteAllTransactions();

		String sql = "SELECT COUNT(*) FROM transaction;";
		int count = jdbcTemplate.queryForObject(sql, new Object[] {}, Integer.class);

		// Assert
		Assert.assertEquals(0, count);
	}
}
