package com.santander.testesantander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.santander")

public class TesteSantanderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TesteSantanderApplication.class, args);
	}

}
