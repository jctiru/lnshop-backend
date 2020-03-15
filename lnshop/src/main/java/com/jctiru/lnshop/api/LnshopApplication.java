package com.jctiru.lnshop.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LnshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LnshopApplication.class, args);
	}

}
