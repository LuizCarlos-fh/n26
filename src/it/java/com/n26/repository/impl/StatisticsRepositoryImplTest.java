package com.n26.repository.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.n26.domain.Transaction;
import com.n26.repository.IStatisticsRepository;

/**
 * The Class StatisticsRepositoryImplTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class StatisticsRepositoryImplTest {

	private static final int TIME_RANGE = 60000;
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private IStatisticsRepository statisticsRepository;
	
	@Test
	public void fetchStatisticsTest() throws ParseException {

		// Arrange
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		String currentTime = dateFormat.format(new Date());
		Date parseCurrentTime = dateFormat.parse(currentTime);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		BigDecimal amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		Long timestamp = new Timestamp(parseCurrentTime.getTime()).getTime();

		String sql = "INSERT INTO transaction (amount, timestamp) VALUES (" + amount + "," + timestamp + ");";
		jdbcTemplate.execute(sql);

		amount = BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP);
		timestamp = new Timestamp(parseCurrentTime.getTime()).getTime();

		sql = "INSERT INTO transaction (amount, timestamp) VALUES (" + amount + "," + timestamp + ");";
		jdbcTemplate.execute(sql);

		Long currentTimestamp = new Timestamp(parseCurrentTime.getTime()).getTime();
		Long sixtySecondsAgo = currentTimestamp - TIME_RANGE;
		
		// Act
		List<Transaction> transactionList = statisticsRepository.fetchStatistics(currentTimestamp, sixtySecondsAgo);

		// Assert
		Assert.assertEquals(2, transactionList.size());
		Assert.assertTrue(amount.equals(transactionList.get(0).getAmount()));
		Assert.assertTrue(timestamp.equals(transactionList.get(0).getTimestamp()));
	}
}
