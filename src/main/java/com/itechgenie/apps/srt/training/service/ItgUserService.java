package com.itechgenie.apps.srt.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.itechgenie.apps.srt.training.dao.ItgUsersMockDao;
import com.itechgenie.apps.srt.training.dto.ItgUsersDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class ItgUserService {

	StopWatch stopWatch = new StopWatch("Service Timestamps");

	@Autowired
	private ItgUsersMockDao itgUsersMockDao;

	public List<ItgUsersDto> getCustomers() {
		stopWatch.start("start getCustomers");
		List<ItgUsersDto> userList = itgUsersMockDao.getCustomers();
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		return userList;
	}

	public Flux<ItgUsersDto> getCustomersStream() {
		stopWatch.start("start getCustomersStream");
		Flux<ItgUsersDto> userList = itgUsersMockDao.getCustomersStream();
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		return userList;
	}

	public Flux<ItgUsersDto> getCustomersList() {
		stopWatch.start("start getCustomersList");
		Flux<ItgUsersDto> userList = itgUsersMockDao.getCustomersList();
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		return userList;
	}

}
