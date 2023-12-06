package com.itechgenie.apps.srt.training;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SrtMain {
	
	public static void main(String[] args) {
		
		holdObservables() ;
		coldObservables();
		
		
		SpringApplication.run(SrtMain.class, args);
		
	}
	
	private static void coldObservables() {
		Observable<String> objs = Observable.just("1","2","3");
		objs.subscribe(txt -> System.out.println(txt));
		
		List myList = Arrays.asList(10,20,30,40);
		
		Observable<Integer> obj2 = Observable.fromIterable(myList) ;
		obj2.subscribe(txt -> System.out.println(txt));
		
		
		Observable<Integer> obj3 = Observable.create(emmiter -> {
			emmiter.onNext(10) ;
			emmiter.onNext(40) ;
			emmiter.onNext(60) ;
			emmiter.onComplete();
		});
		obj3.subscribe(txt -> System.out.println(txt),
				error -> System.err.println("Error: " + error.getMessage()),
				() -> System.out.println("Complete")
				);
	}
	
	private static void holdObservables() {
		ConnectableObservable<String> objs =   Observable.just("1","2","3").publish();;
		objs.subscribe(txt -> System.out.println(txt));
		
		objs.connect();
		 
	}
	
	public Flux<String> fluxApp() {
		List<String> list = Arrays.asList("alex","surya","Mannoj"); // db or remote service
		//return Flux.just("alex","surya","Mannoj");
		return Flux.fromIterable(list).log();
	}
	public Flux<String> fluxAppAllCaps() {
		List<String> list = Arrays.asList("alex","surya","Mannoj"); // db or remote service
		//return Flux.just("alex","surya","Mannoj");
		return Flux.fromIterable(list).map(String::toUpperCase).log() ;
	}
	
	public Flux<String> fluxAppAllCapsFiltered() {
		List<String> list = Arrays.asList("alex","surya","Mannoj"); // db or remote service
		//return Flux.just("alex","surya","Mannoj");
		return Flux.fromIterable(list)
				.map(String::toUpperCase)
				.filter(s -> (s.length() >= 5)).log() ;
	}
	
	public Flux<String> fluxAppAllCapsFilteredFM() {
		List<String> list = Arrays.asList("alex","surya","Mannoj"); // db or remote service
		//return Flux.just("alex","surya","Mannoj");
		return Flux.fromIterable(list)
				.flatMap(s -> splitString(s)).log() ;
	}
	
	public Mono<String> monoApp() {
		 
		return Mono.just("Sameer");
	}
	
	public Flux<String> splitString(String input) {
		 
		return Flux.fromArray(input.split("")) ;
	}

}
