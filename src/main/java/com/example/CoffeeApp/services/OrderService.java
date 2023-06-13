package com.example.CoffeeApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.CoffeeApp.domains.Order;
import com.example.CoffeeApp.domains.User;
import com.example.CoffeeApp.domains.Product;
import com.example.CoffeeApp.repositories.OrderRepo;
import com.example.CoffeeApp.services.ProductService;
import com.example.CoffeeApp.services.UserService;

@Service
public class OrderService {

    private OrderRepo orderrepo;
    private ProductService productservice;
    private UserService userservice;

    public OrderService(OrderRepo orderRepo, ProductService productservice, UserService userservice) {
        this.orderrepo = orderRepo;
        this.userservice = userservice;
        this.productservice = productservice;
    }

    public List<Order> viewOrders() {
        return ((List<Order>) orderrepo.findAll());
    }

    public Order createOrder(Order order) {

        User user = userservice.findById(order.getCustomer().getUserId());
        if (user == null) {
            // Handle the case when the user does not exist
            throw new IllegalArgumentException("Invalid user");
        }
        Product product = productservice.findById(order.getProduct().getid());
        if (product == null) {
            // Handle the case when the product does not exist
            throw new IllegalArgumentException("Invalid product");
        }

        System.out.println(user.getFirstName() + " " + product.getproductName());
        // Set the user and product for the order
        order.setCustomer(user);
        order.setProduct(product);
        return orderrepo.save(order);

    }

}
