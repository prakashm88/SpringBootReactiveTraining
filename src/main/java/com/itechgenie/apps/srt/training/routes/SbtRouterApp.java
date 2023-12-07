package com.itechgenie.apps.srt.training.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.itechgenie.apps.srt.training.handlers.SbtHandlerFunction;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SbtRouterApp {

	@Bean
	RouterFunction<ServerResponse> route(SbtHandlerFunction func) {
		log.info("Inside route function ");
		return RouterFunctions.route(GET("/api/myflux").and(accept(MediaType.APPLICATION_JSON)), func::flux);

	}

	@Bean
	RouterFunction<ServerResponse> getUsers(SbtHandlerFunction handler) {
		log.info("Inside route function getUsers");
		return RouterFunctions.route(GET("/api/users").and(accept(MediaType.TEXT_EVENT_STREAM)), handler::userList);

	}

	@Bean
	RouterFunction<ServerResponse> getUser(SbtHandlerFunction handler) {
		log.info("Inside route function ");
		return RouterFunctions.route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)), handler::userList);
	}

	@Bean
	RouterFunction<ServerResponse> saveUser(SbtHandlerFunction handler) {
		log.info("Inside route function ");
		return RouterFunctions.route(POST("/api/user").and(accept(MediaType.APPLICATION_JSON)), handler::userList);
	}

}
