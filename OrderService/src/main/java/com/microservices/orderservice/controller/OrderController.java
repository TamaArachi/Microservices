package com.microservices.orderservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequest request) {
		
		service.placeOrder(request);
		return "Order placed successfully";
	}

}
