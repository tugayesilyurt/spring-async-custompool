package com.async.example.runner;

import java.util.stream.IntStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.async.example.service.AsyncService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncRunnerMultiple implements CommandLineRunner {

	private final AsyncService asyncService;

	@Override
	public void run(String... args) throws Exception {

		StopWatch sw = new StopWatch();
		sw.start();

		IntStream.range(0,100).forEach(i -> {
			try {
				asyncService.findByIdMultiple(i);
			} catch (InterruptedException e) {
				log.error("Exception " + e.getMessage());
			}
		});

		sw.stop();

		log.info("Duration Multiple  " + sw.getTotalTimeSeconds());

	}

}