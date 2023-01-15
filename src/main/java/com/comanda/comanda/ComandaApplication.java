package com.comanda.comanda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.comanda.comanda.Product.Repository", "com.comanda.comanda.Category.repository"})
public class ComandaApplication {
	public static void main(String[] args) {
		SpringApplication.run(ComandaApplication.class, args);
	}

}
