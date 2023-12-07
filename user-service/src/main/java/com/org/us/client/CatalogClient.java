package com.org.us.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.org.us.dto.OrderDTO;

@FeignClient(value = "catalog-service", url = "${catalog.service.baseUrl}", path = "/orders")
public interface CatalogClient
{
	@GetMapping
	List<OrderDTO> getOrders();

	@GetMapping("/{category}")
	List<OrderDTO> getOrdersByCategory(@PathVariable String category);
}
