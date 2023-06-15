package com.example.CoffeeApp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.CoffeeApp.domains.OrderItems;
import com.example.CoffeeApp.domains.Orders;

public interface OrderItemsRepo extends CrudRepository<OrderItems, String> {

}
