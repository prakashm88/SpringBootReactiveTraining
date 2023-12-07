package com.itechgenie.apps.srt.training.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

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

	public RouterFunction<ServerResponse> route(SbtHandlerFunction func) {
		log.info("Inside route function ");
		return RouterFunctions.route(GET("/api/myflux").and(accept(MediaType.APPLICATION_JSON)), func::flux);

	}

	public RouterFunction<ServerResponse> getUsers(SbtHandlerFunction func) {
		log.info("Inside route function getUsers");
		return RouterFunctions.route(GET("/api/users").and(accept(MediaType.TEXT_EVENT_STREAM)), func::userList);

	}

	public RouterFunction<ServerResponse> getUser(SbtHandlerFunction func) {
		log.info("Inside route function ");
		return RouterFunctions.route(GET("/api/user").and(accept(MediaType.APPLICATION_JSON)), func::userList);
	}
	
	public RouterFunction<ServerResponse> saveUser(SbtHandlerFunction func) {
		log.info("Inside route function ");
		return RouterFunctions.route(POST("/api/user").and(accept(MediaType.APPLICATION_JSON)), func::userList);
	}

}
