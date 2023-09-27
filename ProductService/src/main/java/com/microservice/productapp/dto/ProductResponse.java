package com.microservice.productapp.dto;

import java.math.BigDecimal;

import com.microservice.productapp.model.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

	private String id;
	private String name;
	private String description;
	private BigDecimal price;
	

}
