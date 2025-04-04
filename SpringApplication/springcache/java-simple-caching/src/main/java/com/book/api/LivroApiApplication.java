package com.book.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LivroApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LivroApiApplication.class, args);
	}

}
