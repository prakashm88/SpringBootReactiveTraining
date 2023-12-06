package com.itechgenie.apps.srt.training;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
public class SbrTrainingTest {

	SrtMain app = new SrtMain();

	@Autowired
	WebTestClient webTestClient;

	@Test
	void testFluxApis() {

		Flux<Integer> fluxInt = webTestClient.get().uri("/flux/2").exchange().expectStatus().isOk()
				.returnResult(Integer.class).getResponseBody();

		StepVerifier.create(fluxInt).expectSubscription().expectNext(1).expectNext(2).expectNext(3).verifyComplete();

	}

	@Test
	void testFlux() {
		// given

		// when
		Flux<String> namesdata = app.fluxApp();

		// then
		StepVerifier.create(namesdata).expectNext("alex")
				// .expectNextCount(2)
				.expectNext("surya").expectNext("Mannoj").expectNextCount(0).verifyComplete();

		Flux<String> namesdata2 = app.fluxAppAllCaps();

		StepVerifier.create(namesdata2).expectNext("ALEX").expectNext("SURYA").expectNext("MANNOJ").verifyComplete();

		Flux<String> namesdata3 = app.fluxAppAllCapsFiltered();

		StepVerifier.create(namesdata3).expectNext("SURYA").expectNext("MANNOJ").verifyComplete();

		Flux<String> namesdata4 = app.fluxAppAllCapsFilteredFM();

		StepVerifier.create(namesdata4).expectNext("a");

	}

}