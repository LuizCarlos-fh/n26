package com.n26.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The Class Transaction.
 */
@Entity
public class Transaction {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	/** The amount. */
	private BigDecimal amount;

	/** The timestamp. */
	private Long timestamp;

	/**
	 * Instantiates a new transaction.
	 */
	public Transaction() {
	}

	/**
	 * Instantiates a new transaction.
	 *
	 * @param amount the amount
	 * @param timestamp the timestamp
	 */
	public Transaction(BigDecimal amount, Long timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Transaction [getAmount()=" + getAmount() + ", getTimestamp()=" + getTimestamp() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
