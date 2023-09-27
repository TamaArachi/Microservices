package com.microservices.orderservice.dto;

import java.util.List;

import com.microservices.orderservice.model.OrderLineItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	
	List<OrderLineItemsdto> orderLineItemsDtoList;

}
