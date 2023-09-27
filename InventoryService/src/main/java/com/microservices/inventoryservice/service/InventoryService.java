package com.microservices.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.inventoryservice.dto.InventoryResponse;
import com.microservices.inventoryservice.repository.InventoryRepository;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository repository;
	
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skewCode)
	{
		return repository.findBySkewCodeIn(skewCode)
				.stream()
				.map(inventory -> 
					InventoryResponse.builder()
					.skewCode(inventory.getSkewCode())
					.isInStock(inventory.getQuantity()>0)
					.build()
					
				).toList();
	}

}
