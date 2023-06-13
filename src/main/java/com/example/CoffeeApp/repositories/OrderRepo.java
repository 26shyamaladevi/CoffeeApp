package com.example.CoffeeApp.repositories;

import com.example.CoffeeApp.domains.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, String> {

}
