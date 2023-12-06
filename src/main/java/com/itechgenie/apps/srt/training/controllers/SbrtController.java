package com.itechgenie.apps.srt.training.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
public class SbrtController {
	
	@GetMapping("/flux/1")
	public Flux<Integer> returnFlux() {
		return Flux.just(1,2,3,4,5,6,7,8).log(); 
	}
	
	@GetMapping(value="/flux/2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Integer> returnFlux2() {
		return Flux.just(1,2,3).log(); 
	}

}
