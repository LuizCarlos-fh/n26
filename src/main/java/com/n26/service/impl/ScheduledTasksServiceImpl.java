package com.n26.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.n26.repository.ITransactionsRepository;
import com.n26.service.IScheduledTasksService;

/**
 * The Class ScheduledTasksServiceImpl.
 */
@Service
public class ScheduledTasksServiceImpl implements IScheduledTasksService {

	/** The transactions repository. */
	@Autowired
	private ITransactionsRepository transactionsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.n26.service.IScheduledTasksService#scheduleTask()
	 */
	@Override
	@Scheduled(fixedRate = 60000)
	public void scheduleTask() {
		transactionsRepository.deleteAllTransactions();
	}

}
