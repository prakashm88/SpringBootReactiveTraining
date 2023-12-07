package com.itechgenie.apps.srt.training.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.itechgenie.apps.srt.training.dto.ItgUsersDto;
import com.itechgenie.apps.srt.training.service.ItgUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SbtHandlerFunction {

	@Autowired
	private ItgUserService itgUserService;

	public Mono<ServerResponse> flux(ServerRequest request) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Flux.just(1, 2, 3, 4).log(),
				Integer.class);

	}

	public Mono<ServerResponse> userList(ServerRequest request) {

		Flux<ItgUsersDto> userList = itgUserService.getCustomersList();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userList, ItgUsersDto.class);

	}

	public Mono<ServerResponse> findCustomer(ServerRequest request) {

		int userid = Integer.parseInt(request.pathVariable("id"));

		Mono<ItgUsersDto> userDto = itgUserService.getCustomersList().filter(c -> (c.getId() == userid)).next();
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userDto, ItgUsersDto.class);

	}

	public Mono<ServerResponse> saveUser(ServerRequest request) {

		Mono<ItgUsersDto> inputCustomer = request.bodyToMono(ItgUsersDto.class);

		Mono<String> str = inputCustomer.map(u -> u.getId() + ":" + u.getName());
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(str, String.class);

	}

}
