package com.microservice.productapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.productapp.dto.ProductRequest;
import com.microservice.productapp.dto.ProductResponse;
import com.microservice.productapp.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
	
	
	private final ProductService service;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody ProductRequest request) {
		
		service.createProduct(request);
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponse> getProducts(){
		
	  List<ProductResponse>	 response =  service.getAllProducts();
	  return response;
	}
	
	

}
