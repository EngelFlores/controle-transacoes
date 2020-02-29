package com.acelera.tcc.group03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories (basePackages = "com.acelera.tcc.group03.repositories")
public class TransactionSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransactionSystemApplication.class, args);
	}
}