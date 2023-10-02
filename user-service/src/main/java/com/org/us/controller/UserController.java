package com.org.us.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.us.client.CatalogClient;
import com.org.us.dto.OrderDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/user-service")
public class UserController
{
	private CatalogClient catalogClient;

	private static final String USER_SERVICE = "userService";

	// private int attempt = 1;

	public UserController(CatalogClient catalogClient)
	{
		this.catalogClient = catalogClient;
	}

	@GetMapping("/displayOrders")
	@CircuitBreaker(name = USER_SERVICE, fallbackMethod = "getAllAvailableProducts")
	// @Retry(name = USER_SERVICE, fallbackMethod = "getAllAvailableProducts")
	public List<OrderDTO> displayOrders(@RequestParam("category") String category)
	{
		// System.out.println("retry method called " + attempt++ + " times " + " at " + new Date());

		if(StringUtils.hasText(category))
		{
			return catalogClient.getOrdersByCategory(category);
		}

		return catalogClient.getOrders();
	}

	public List<OrderDTO> getAllAvailableProducts(Exception e)
	{
		return Stream.of(
				new OrderDTO(119, "LED TV", "electronics", "white", 45000),
				new OrderDTO(345, "Headset", "electronics", "black", 7000),
				new OrderDTO(475, "Sound bar", "electronics", "black", 13000),
				new OrderDTO(574, "Puma Shoes", "foot wear", "black & white", 4600),
				new OrderDTO(678, "Vegetable chopper", "kitchen", "blue", 999),
				new OrderDTO(532, "Oven Gloves", "kitchen", "gray", 745))
				.collect(Collectors.toList());
	}
}
