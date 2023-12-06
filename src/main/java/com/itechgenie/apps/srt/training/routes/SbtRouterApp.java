package com.itechgenie.apps.srt.training.routes;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.itechgenie.apps.srt.training.handlers.SbtHandlerFunction;

@Configuration
public class SbtRouterApp {

	public RouterFunction<ServerResponse> route(SbtHandlerFunction func) {
		return RouterFunctions.route(GET("/verizon/myflux").and(accept(MediaType.APPLICATION_JSON)),
				func::flux);

	}

}
