package com.microservices.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservices.orderservice.dto.InventoryResponse;
import com.microservices.orderservice.dto.OrderLineItemsdto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderLineItem;
import com.microservices.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	
	private final OrderRepository repository;
	
	private final WebClient.Builder webClientBuilder;
	
	
	public void placeOrder(OrderRequest request) {
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		 List<OrderLineItem> orderLineItem =  request.getOrderLineItemsDtoList().stream()
		.map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();
		 
		 order.setOrderlinesitemList(orderLineItem);
		 
		 List<String> skewCodelist =  order.getOrderlinesitemList()
		 		.stream().map(OrderLineItem::getSkewCode).toList();
		
		
		
		//calling inventory service
		InventoryResponse[] inventoryResponseArray =  webClientBuilder.build().get()
											.uri("http://inventory-service/api/inventory"
														,uriBuilder -> uriBuilder.queryParam("skewCode", skewCodelist).build())
											.retrieve()
											.bodyToMono(InventoryResponse[].class)
											.block();
		
		
		boolean isInStock =   Arrays.stream(inventoryResponseArray)
		.allMatch(InventoryResponse::isInStock);
		
		if(isInStock)
		{
			repository.save(order);
		}else
		{
			throw new IllegalArgumentException("Product is not in stock. Pls try later");
			
		}
					
		
		
	}

	private OrderLineItem mapToDto(OrderLineItemsdto orderLineItemsDto) {
		// TODO Auto-generated method stub
		return OrderLineItem.builder().id(orderLineItemsDto.getId())
		.price(orderLineItemsDto.getPrice())                                                         
		.quantity(orderLineItemsDto.getQuantity())
		.skewCode(orderLineItemsDto.getSkewCode()).build();
		
		
	}

}
