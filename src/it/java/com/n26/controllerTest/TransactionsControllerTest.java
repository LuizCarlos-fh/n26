package com.n26.controllerTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.n26.controller.TransactionsController;
import com.n26.domain.TransactionRequest;
import com.n26.service.ITransactionsService;

public class TransactionsControllerTest
{
	@Mock
	private ITransactionsService transactionsService;

	@InjectMocks
	TransactionsController transactionsController = new TransactionsController();

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addTransaction()
	{
		// Arrange
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		TransactionRequest request = new TransactionRequest("10", dateFormat.format(new Date()));
		Mockito.when(transactionsService.addTransaction(request)).thenReturn(new ResponseEntity<String>(HttpStatus.NO_CONTENT));
		
		// Act
		ResponseEntity<String> response = transactionsController.addTransaction(request);
		
		// Assert
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void addTransactionWithBadRequest()
	{
		// Arrange
		Mockito.when(transactionsService.addTransaction(null)).thenReturn(new ResponseEntity<String>(HttpStatus.BAD_REQUEST));
		
		// Act
		ResponseEntity<String> response = transactionsController.addTransaction(null);
		
		// Assert
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void addTransactionWithException()
	{
		// Arrange
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		TransactionRequest request = new TransactionRequest("10", dateFormat.format(new Date()));
		Mockito.when(transactionsService.addTransaction(request)).thenThrow(new NullPointerException("Error occurred"));
		
		// Act
		ResponseEntity<String> response = transactionsController.addTransaction(request);
		
		// Assert
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
	
	@Test
	public void deleteAllTransactions()
	{
		// Arrange
		Mockito.doNothing().when(transactionsService).deleteAllTransactions();
		
		// Act
		ResponseEntity<String> response = transactionsController.deleteAllTransactions();
		
		// Assert
		Assert.assertTrue(response.getStatusCode().equals(HttpStatus.NO_CONTENT));
	}
}
