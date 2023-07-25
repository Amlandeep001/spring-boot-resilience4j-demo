package com.org.us.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.org.us.dto.OrderDTO;

@FeignClient(value = "user-service", url = "http://localhost:9191", path = "/orders")
public interface UserClient
{
	@GetMapping
	public List<OrderDTO> getOrders();

	@GetMapping("/{category}")
	List<OrderDTO> getOrdersByCategory(@PathVariable String category);
}
