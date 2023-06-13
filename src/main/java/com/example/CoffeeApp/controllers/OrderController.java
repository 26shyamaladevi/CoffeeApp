package com.example.CoffeeApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.CoffeeApp.domains.Order;
import com.example.CoffeeApp.services.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderservice;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderservice.viewOrders();
    }

    @PostMapping("/orders/add")
    public ResponseEntity<Order> addNewOrder(@RequestBody Order order) {
        Order createdOrder = orderservice.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

}
