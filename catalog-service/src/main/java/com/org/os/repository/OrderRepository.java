package com.org.os.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.os.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>
{
	List<Order> findByCategory(String category);
}
