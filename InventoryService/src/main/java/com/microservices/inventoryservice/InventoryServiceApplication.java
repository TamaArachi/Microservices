package com.microservices.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner load(InventoryRepository inventoryRepository) {
		
		return args -> {
			Inventory i1 = new Inventory();
			i1.setSkewCode("iphone_13");
			i1.setQuantity(100);
			
			Inventory i2 = new Inventory();
			i2.setSkewCode("iphone_13_red");
			i2.setQuantity(0);
			
			inventoryRepository.save(i1);
			inventoryRepository.save(i2);
		};
	}

}
