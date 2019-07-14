package com.n26.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.domain.StatisticsResponse;
import com.n26.domain.Transaction;
import com.n26.repository.IStatisticsRepository;
import com.n26.service.IStatisticsService;

/**
 * The Class StatisticsServiceImpl.
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService {

	private static final Logger log = LogManager.getLogger(StatisticsServiceImpl.class);

	private static final int TIME_RANGE = 60000;

	/** The statistics repository. */
	@Autowired
	private IStatisticsRepository statisticsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.service.IStatisticsService#fetchStatistics()
	 */
	@Override
	public StatisticsResponse fetchStatistics() {

		Long currentTimestamp = null;

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			String currentTime = dateFormat.format(new Date());
			Date parseCurrentTime = dateFormat.parse(currentTime);
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			Timestamp timestamp = new Timestamp(parseCurrentTime.getTime());
			currentTimestamp = timestamp.getTime();
			//currentTimestamp = timestamp.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)  * 1000;
		} catch (Exception ex) {
			log.error("Timestamp parse error. " + ex.getMessage());
			return new StatisticsResponse();
		}

		Long sixtySecondsAgo = currentTimestamp - TIME_RANGE;

		BigDecimal sum = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal avg = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal max = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal min = new BigDecimal(Double.MAX_VALUE);

		List<Transaction> transactionList = statisticsRepository.fetchStatistics(currentTimestamp, sixtySecondsAgo);
		Long count = (long) transactionList.size();

		if (count == 0) {
			min = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		if (count > 0) {
			BigDecimal amount;

			for (Transaction item : transactionList) {
				amount = item.getAmount();

				sum = sum.add(amount);

				if (amount.compareTo(min) == -1) {
					min = amount;
				}

				if (amount.compareTo(max) == 1) {
					max = amount;
				}
			}

			avg = sum.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);
		}

		return new StatisticsResponse(sum.toString(), avg.toString(), max.toString(), min.toString(), count);
	}

}
