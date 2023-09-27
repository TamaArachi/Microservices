package com.microservice.productapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.productapp.dto.ProductRequest;
import com.microservice.productapp.dto.ProductResponse;
import com.microservice.productapp.model.Product;
import com.microservice.productapp.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	
	private final ProductRepository repo;
	
	//creates product in repo class
	public void createProduct(ProductRequest request)
	{
		Product product = Product.builder().name(request.getName()).description(request.getDescription())
				         .price(request.getPrice()).build();
		
		repo.save(product);
		
		log.info("Product {} is saved : " , product.getId());
		
		
		
	}

	public List<ProductResponse> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> productList = repo.findAll();
		
		return productList.stream().map(product -> mapToProduct(product) ).toList();	
	}

	private ProductResponse mapToProduct(Product product) {
		// TODO Auto-generated method stub
		return ProductResponse.builder().id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}
