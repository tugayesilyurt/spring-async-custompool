package com.async.example.runner;

import java.util.concurrent.CompletableFuture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.async.example.dto.Product;
import com.async.example.service.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncRunner implements CommandLineRunner {

	private final AsyncService asyncService;

	@Override
	public void run(String... args) throws Exception {

		StopWatch sw = new StopWatch();
		sw.start();

		CompletableFuture<Product> product1 = asyncService.findById(1l);
		CompletableFuture<Product> product2 = asyncService.findById(2l);
		CompletableFuture<Product> product3 = asyncService.findById(3l);

		CompletableFuture.allOf(product1, product2, product3).join();

		sw.stop();

		log.info("Duration  " + sw.getTotalTimeSeconds());
		log.info("--> " + product1.get());
		log.info("--> " + product2.get());
		log.info("--> " + product3.get());

	}

}