package com.n26.service.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.n26.domain.TransactionRequest;
import com.n26.repository.ITransactionsRepository;
import com.n26.service.ITransactionsService;

/**
 * The Class TransactionsServiceImplTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsServiceImplTest
{

	@Mock
	private ITransactionsRepository transactionsRepository;

	@InjectMocks
	ITransactionsService transactionsService = new TransactionsServiceImpl();

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Adds the transaction with invalid amount.
	 */
	@Test
	public void addTransactionWithInvalidAmount()
	{

		TransactionRequest request = createTransactionRequest("test", "2018-10-08T18:11:00.000Z");
		ResponseEntity<String> response = transactionsService.addTransaction(request);
		Assert.assertTrue(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build().equals(response));
	}

	/**
	 * Adds the transaction with invalid timestamp.
	 */
	@Test
	public void addTransactionWithInvalidTimestamp()
	{

		TransactionRequest request = createTransactionRequest("test", "test");
		ResponseEntity<String> response = transactionsService.addTransaction(request);
		Assert.assertTrue(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build().equals(response));
	}

	/**
	 * Adds the transaction date older than sixty seconds.
	 */
	@Test
	public void addTransactionDateOlderThanSixtySeconds()
	{

		TransactionRequest request = createTransactionRequest("10.342", "2017-01-01T01:00:00.000Z");
		ResponseEntity<String> response = transactionsService.addTransaction(request);
		Assert.assertTrue(ResponseEntity.status(HttpStatus.NO_CONTENT).build().equals(response));
	}

	/**
	 * Adds the transaction future date.
	 */
	@Test
	public void addTransactionFutureDate()
	{

		TransactionRequest request = createTransactionRequest("10.342", "2020-01-01T01:00:00.000Z");
		ResponseEntity<String> response = transactionsService.addTransaction(request);
		Assert.assertTrue(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build().equals(response));
	}

	/**
	 * Adds the transaction.
	 */
	@Test
	public void addTransactionSuccessCase()
	{

		// Arrange
		Mockito.doNothing().when(transactionsRepository).saveTransaction(BigDecimal.TEN, Long.valueOf("1538950980000"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		TransactionRequest request = createTransactionRequest("10", dateFormat.format(new Date()));

		// Act
		ResponseEntity<String> response = transactionsService.addTransaction(request);

		// Assert
		Assert.assertTrue(ResponseEntity.status(HttpStatus.CREATED).build().equals(response));
	}

	/**
	 * Delete all transactions.
	 */
	@Test
	public void deleteAllTransactions()
	{

		// Arrange
		Mockito.doNothing().when(transactionsRepository).deleteAllTransactions();

		// Act
		transactionsService.deleteAllTransactions();
	}

	/**
	 * Creates the transaction request.
	 *
	 * @param amount the amount
	 * @param timestamp the timestamp
	 * @return the transaction request
	 */
	private TransactionRequest createTransactionRequest(String amount, String timestamp)
	{
		return new TransactionRequest(amount, timestamp);
	}
}
