package com.n26.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.n26.domain.TransactionRequest;
import com.n26.service.ITransactionsService;

/**
 * The Class TransactionsController.
 */
@RestController
public class TransactionsController {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(TransactionsController.class);

	/** The transactions service. */
	@Autowired
	private ITransactionsService transactionsService;

	/**
	 * Adds the transaction.
	 *
	 * @param request the request
	 * @return the transaction response
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> addTransaction(@RequestBody TransactionRequest request) {

		try {
			if (request == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}

			return transactionsService.addTransaction(request);
		} catch (Exception ex) {
			log.error("An error was encountered. Please contact your system administrator. " + ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	/**
	 * Deletes all transactions.
	 *
	 * @return the transaction response
	 */
	@RequestMapping(value = "/transactions", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<String> deleteAllTransactions() {

		try {
			transactionsService.deleteAllTransactions();
		} catch (Exception ex) {
			log.error("An error was encountered. Please contact your system administrator. " + ex.getMessage());
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
