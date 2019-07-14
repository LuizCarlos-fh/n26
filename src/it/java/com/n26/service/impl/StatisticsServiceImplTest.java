package com.n26.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.n26.domain.StatisticsResponse;
import com.n26.domain.Transaction;
import com.n26.repository.IStatisticsRepository;
import com.n26.service.IStatisticsService;

/**
 * The Class StatisticsServiceImplTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceImplTest {

	@Mock
	private IStatisticsRepository statisticsRepository;
	
	@InjectMocks
	IStatisticsService statisticsService = new StatisticsServiceImpl();
	
	 @Before
	  public void init(){
	    MockitoAnnotations.initMocks(this);
	  }
	 
	@Test
	public void fetchStatistics() {

		// Arrange
		List<Transaction> transactionList = new ArrayList<Transaction>();
		transactionList.add(new Transaction(BigDecimal.TEN, Long.valueOf("1538950980000")));
		
		Mockito.when(statisticsRepository.fetchStatistics(Mockito.anyLong(), Mockito.anyLong())).thenReturn(transactionList);
		
		// Act
		StatisticsResponse response = statisticsService.fetchStatistics();

		// Assert
		Assert.assertTrue("10.00".equals(response.getSum()));
		Assert.assertTrue("10.00".toString().equals(response.getAvg()));
		Assert.assertTrue("10".toString().equals(response.getMax()));
		Assert.assertTrue("10".toString().equals(response.getMin()));
		
	}
}
