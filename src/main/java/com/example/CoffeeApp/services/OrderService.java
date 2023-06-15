package com.example.CoffeeApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.CoffeeApp.services.*;
import com.example.CoffeeApp.domains.*;
import com.example.CoffeeApp.repositories.OrderItemsRepo;
import com.example.CoffeeApp.repositories.OrdersRepo;
import com.example.CoffeeApp.repositories.PaymentRepo;;

@Service
public class OrderService {

    private UserService userService;
    private ProductService productService;
    private OrdersRepo ordersRepo;
    private PaymentRepo paymentRepo;
    private OrderItemsRepo orderItemsRepo;

    public OrderService(UserService userService, ProductService productService, OrdersRepo orderepo,
            OrderItemsRepo orderItemsRepo,
            PaymentRepo paymentRepo) {
        this.userService = userService;
        this.productService = productService;
        this.ordersRepo = orderepo;
        this.orderItemsRepo = orderItemsRepo;
        this.paymentRepo = paymentRepo;
    }

    public List<Orders> viewOrders(Long orderId) {

        return (List<Orders>) ordersRepo.findByOrderId(orderId);

    }

    public boolean createOrder(Orders order) {
        User customer = userService.findById(order.getCustomer().getUserId());
        if (customer == null) {
            throw new IllegalArgumentException("Invalid Customer");
        }
        Payment payment = paymentRepo.findByPaymentMethod(order.getPaymentMethod());

        if (payment == null) {
            throw new IllegalArgumentException("Invalid Customer");
        }

        Orders newOrder = new Orders();
        newOrder.setCustomer(customer);
        newOrder.setCustomerEmailId(customer.getEmailId());
        newOrder.setPaymentMethod(payment.getPaymentMethod());

        // Save the Orders entity to make it managed
        ordersRepo.save(newOrder);

        // Set the managed Orders entity to the OrderItems
        for (OrderItems orderItem : order.getOrderItems()) {
            // Check if the Product Exsist in the product table
            if (productService.existsById(orderItem.getpId())) {
                Product product = productService.findById(orderItem.getpId());
                orderItem.setpId(product.getid());
                orderItem.setpName(product.getproductName());
                orderItem.setPrice(product.getprice());
                orderItem.setOrders(newOrder);
            } else {
                throw new IllegalArgumentException("Invalid Product");
            }
        }

        // Save the OrderItems entities
        orderItemsRepo.saveAll(order.getOrderItems());

        return true;

    }

    public boolean updateOrder(long orderId, Orders updatedOrder) {
        Optional<Orders> optionalOrder = ordersRepo.findByOrderId(orderId);

        if (optionalOrder.isPresent()) {
            Orders existingOrder = optionalOrder.get();

            // Update the properties of the existing order with the new values

            for (OrderItems orderItem : existingOrder.getOrderItems()) {
                // Check if the Product Exsist in the product table
                if (productService.existsById(orderItem.getpId())) {
                    Product product = productService.findById(orderItem.getpId());
                    orderItem.setpId(product.getid());
                    orderItem.setpName(product.getproductName());
                    orderItem.setPrice(product.getprice());
                    orderItem.setOrders(updatedOrder);
                }

            }
            return true;
        }

        else {
            return false;
        }

    }

    public Orders getOrderById(Long orderId) {
        Optional<Orders> optionalOrder = ordersRepo.findByOrderId(orderId);
        return optionalOrder.orElse(null);
    }

    public void deleteOrder(Orders order) {
        ordersRepo.delete(order);
    }

}
