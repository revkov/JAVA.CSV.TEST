package com.brekhin.smartSoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SmartSoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSoftApplication.class, args);
	}

}
