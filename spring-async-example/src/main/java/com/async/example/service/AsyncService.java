package com.async.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.async.example.dto.Product;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AsyncService {

	private final static List<Product> products = new ArrayList<>();

	static {
		products.add(new Product(1l, "async-1"));
		products.add(new Product(2l, "async-2"));
		products.add(new Product(3l, "async-3"));
		products.add(new Product(4l, "async-4"));
	}

	@Async("threadPoolAsync")
	public CompletableFuture<Product> findById(long id) throws InterruptedException {
		
		Product product = products.stream().filter(item -> item.getId() == id).findFirst()
				.orElseThrow(() -> new RuntimeException("Not Exist!"));
		log.info("Thread Name : " + Thread.currentThread().getName());
		Thread.sleep(20000);
		return CompletableFuture.completedFuture(product);
		
	}
	
	@Async("threadPoolAsync")
	public void findByIdMultiple(long id) throws InterruptedException {
		
		Product product = products.stream().filter(item -> item.getId() == id).findFirst()
				.orElse(Product.builder().id(20l).name("custom").build());
		log.info("Thread Name : " + Thread.currentThread().getName());
		log.info("Product : " + product);
		Thread.sleep(10000);
		
	}

}
