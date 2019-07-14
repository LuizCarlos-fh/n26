package com.n26.domain;

/**
 * The Class Transaction.
 */
public class TransactionRequest {

	/** The amount. */
	private String amount;

	/** The timestamp. */
	private String timestamp;

	/**
	 * Instantiates a new transaction.
	 */
	public TransactionRequest() {
	}

	/**
	 * Instantiates a new transaction.
	 *
	 * @param amount the amount
	 * @param timestamp the timestamp
	 */
	public TransactionRequest(String amount, String timestamp) {
		this.amount = amount;
		this.timestamp = timestamp;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(String timestamp) {
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
