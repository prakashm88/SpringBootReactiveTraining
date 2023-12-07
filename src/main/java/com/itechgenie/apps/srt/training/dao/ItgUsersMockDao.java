package com.itechgenie.apps.srt.training.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.itechgenie.apps.srt.training.dto.ItgUsersDto;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class ItgUsersMockDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			log.error("Exception in adding sleep: " + e.getMessage(), e);

		}
	}

	public List<ItgUsersDto> getCustomers() {
		return IntStream.rangeClosed(1, 10).peek(ItgUsersMockDao::sleepExecution).peek(i -> System.out.println(i))
				.mapToObj(i -> new ItgUsersDto(i, "customer " + i)).collect(Collectors.toList());
	}

	public Flux<ItgUsersDto> getCustomersStream() {
		return Flux.range(1, 10).delayElements(Duration.ofSeconds(1)).doOnNext(i -> System.out.println(i))
				.map(i -> new ItgUsersDto(i, "customer " + i));
	}

	public Flux<ItgUsersDto> getCustomersList() {
		return Flux.range(1, 10).doOnNext(i -> System.out.println(i)).map(i -> new ItgUsersDto(i, "customer " + i));
	}

}
