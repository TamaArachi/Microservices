package com.microservice.productapp;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productapp.dto.ProductRequest;
import com.microservice.productapp.repository.ProductRepository;
import com.microservice.productapp.service.ProductService;


@Testcontainers

@SpringBootTest
@AutoConfigureMockMvc
class ProductAppMicroServiceApplicationTests {
	
	
	@Autowired
	private MockMvc mockmvc;
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private ProductService service;
	
	
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.0.10"));
	
	
	@DynamicPropertySource
	public static void setProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}


	@Test
	void shouldCreateProduct() throws Exception {
		
		ProductRequest request = getProductRequest();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String productString = mapper.writeValueAsString(request);
		
		this.mockmvc.perform(MockMvcRequestBuilders.post("/api/products")
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				.content(productString)
				).andExpect(status().isCreated());
		
		
		Assertions.assertEquals(1, repository.findAll().size());
		
		
	}
	
	private ProductRequest getProductRequest() {
		
		return ProductRequest.builder().name("Iphone 13")
				.description("IPhone 13")
				.price(BigDecimal.valueOf(1200)).build();
	}
	
	
	@Test
	void testgetProducts() throws Exception {
		this.mockmvc.perform(MockMvcRequestBuilders.get("/api/products"))
		.andExpect(status().isOk());

		
	}

}
