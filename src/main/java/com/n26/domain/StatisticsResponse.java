package com.n26.domain;

/**
 * The Class StatisticsResult.
 */
public class StatisticsResponse {

	/** The sum. */
	private String sum;

	/** The avg. */
	private String avg;

	/** The max. */
	private String max;

	/** The min. */
	private String min;

	/** The count. */
	private Long count;

	/**
	 * Instantiates a new statistics result.
	 */
	public StatisticsResponse() {
	}

	/**
	 * Instantiates a new statistics result.
	 *
	 * @param sum the sum
	 * @param avg the avg
	 * @param max the max
	 * @param min the min
	 * @param count the count
	 */
	public StatisticsResponse(String sum, String avg, String max, String min, Long count) {
		this.sum = sum;
		this.avg = avg;
		this.max = max;
		this.min = min;
		this.count = count;
	}

	/**
	 * Gets the sum.
	 *
	 * @return the sum
	 */
	public String getSum() {
		return sum;
	}

	/**
	 * Sets the sum.
	 *
	 * @param sum the new sum
	 */
	public void setSum(String sum) {
		this.sum = sum;
	}

	/**
	 * Gets the avg.
	 *
	 * @return the avg
	 */
	public String getAvg() {
		return avg;
	}

	/**
	 * Sets the avg.
	 *
	 * @param avg the new avg
	 */
	public void setAvg(String avg) {
		this.avg = avg;
	}

	/**
	 * Gets the max.
	 *
	 * @return the max
	 */
	public String getMax() {
		return max;
	}

	/**
	 * Sets the max.
	 *
	 * @param max the new max
	 */
	public void setMax(String max) {
		this.max = max;
	}

	/**
	 * Gets the min.
	 *
	 * @return the min
	 */
	public String getMin() {
		return min;
	}

	/**
	 * Sets the min.
	 *
	 * @param min the new min
	 */
	public void setMin(String min) {
		this.min = min;
	}

	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(Long count) {
		this.count = count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatisticsResult [getSum()=" + getSum() + ", getAvg()=" + getAvg() + ", getMax()=" + getMax()
				+ ", getMin()=" + getMin() + ", getCount()=" + getCount() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
