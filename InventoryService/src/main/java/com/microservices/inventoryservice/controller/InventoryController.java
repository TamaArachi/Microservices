package com.microservices.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.inventoryservice.dto.InventoryResponse;
import com.microservices.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryResponse> isinStock(@RequestParam List<String> skewCode ) {
		
		return service.isInStock(skewCode);
	}

}
