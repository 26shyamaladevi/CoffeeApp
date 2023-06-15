package com.example.CoffeeApp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

//import com.example.CoffeeApp.domains.OrderItems;
import com.example.CoffeeApp.domains.Orders;

public interface OrdersRepo extends CrudRepository<Orders, String> {

    // List<Orders> findByOrderId(long orderId);

    Optional<Orders> findByOrderId(long orderId);
}
