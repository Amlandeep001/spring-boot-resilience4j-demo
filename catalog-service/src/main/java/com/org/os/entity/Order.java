package com.org.os.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ORDERS_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order
{

	@Id
	@GeneratedValue
	int id;
	String name;
	String category;
	String color;
	double price;

	public Order(String name, String category, String color, double price)
	{
		this.name = name;
		this.category = category;
		this.color = color;
		this.price = price;
	}
}
