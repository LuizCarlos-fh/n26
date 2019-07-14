package com.n26.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.n26.domain.TransactionRequest;
import com.n26.repository.ITransactionsRepository;
import com.n26.service.ITransactionsService;

/**
 * The Class TransactionsServiceImpl.
 */
@Service
public class TransactionsServiceImpl implements ITransactionsService {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(TransactionsServiceImpl.class);

	/** The Constant TIME_RANGE. */
	private static final int TIME_RANGE = 60000;

	/** The transactions repository. */
	@Autowired
	private ITransactionsRepository transactionsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.service.ITransactionsService#addTransaction(com.n26.domain.TransactionRequest)
	 */
	@Override
	public ResponseEntity<String> addTransaction(TransactionRequest transaction) {

		// Parse amount
		BigDecimal amount = null;

		try {
			amount = new BigDecimal(transaction.getAmount()).setScale(2, RoundingMode.HALF_UP);
		} catch (Exception ex) {
			log.error("Amount parse error. " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		// Parse timestamp
		Long transactionTimestamp = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

		try {
			Date parsedDate = dateFormat.parse(transaction.getTimestamp());
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			Timestamp timestamp = new Timestamp(parsedDate.getTime());
			transactionTimestamp = timestamp.getTime();
			//transactionTimestamp = timestamp.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)  * 1000;
		} catch (Exception ex) {
			log.error("Timestamp parse error. " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		// Validate timestamp
		Long currentTimestamp = null;

		try {
			String currentTime = dateFormat.format(new Date());
			Date parseCurrentTime = dateFormat.parse(currentTime);
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			Timestamp timestamp = new Timestamp(parseCurrentTime.getTime());
			currentTimestamp = timestamp.getTime();
			//currentTimestamp = timestamp.toLocalDateTime().toEpochSecond(ZoneOffset.UTC)  * 1000;
		} catch (Exception ex) {
			log.error("Timestamp parse error. " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		Long sixtySecondsAgo = currentTimestamp - TIME_RANGE;

		if (transactionTimestamp < sixtySecondsAgo) {

			// Transaction date is older than 60 seconds
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

		if (transactionTimestamp > currentTimestamp) {

			// Transaction date is in the future
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}

		transactionsRepository.saveTransaction(amount, transactionTimestamp);

		// Success case
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.service.ITransactionsService#deleteAllTransactions()
	 */
	@Override
	public void deleteAllTransactions() {
		transactionsRepository.deleteAllTransactions();
	}

}
