package com.n26.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.n26.domain.StatisticsResponse;
import com.n26.service.IStatisticsService;

/**
 * The Class StatisticsController.
 */
@RestController
public class StatisticsController {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(StatisticsController.class);

	/** The statistics service. */
	@Autowired
	private IStatisticsService statisticsService;

	/**
	 * Fetches the statistics.
	 *
	 * @return the statistics response
	 */
	@GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public StatisticsResponse fetchStatistics() {

		StatisticsResponse response = new StatisticsResponse();

		try {
			response = statisticsService.fetchStatistics();
		} catch (Exception ex) {
			log.error("An error was encountered. Please contact your system administrator. " + ex.getMessage());
		}

		return response;
	}

}
