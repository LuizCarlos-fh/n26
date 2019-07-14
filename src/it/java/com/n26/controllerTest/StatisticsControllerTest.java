package com.n26.controllerTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.n26.controller.StatisticsController;
import com.n26.domain.StatisticsResponse;
import com.n26.service.IStatisticsService;

@SpringBootTest
public class StatisticsControllerTest
{
	@Mock
	private IStatisticsService statisticsService;

	@InjectMocks
	StatisticsController statisticsController = new StatisticsController();

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchStatistics()
	{

		// Arrange
		StatisticsResponse statisticsResponse = new StatisticsResponse();
		statisticsResponse.setAvg("10");
		Mockito.when(statisticsService.fetchStatistics()).thenReturn(statisticsResponse);

		// Act
		StatisticsResponse response = statisticsController.fetchStatistics();

		// Assert
		Assert.assertTrue(response.getAvg().equals("10"));
	}
}
