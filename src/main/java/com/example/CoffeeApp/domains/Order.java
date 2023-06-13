package com.example.CoffeeApp.domains;

import jakarta.persistence.*;

@Entity
@Table(name = "coffee_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User customer;

    // Derived from the associated User object
    @Transient
    private String customerEmail;

    @ManyToOne
    @JoinColumn(name = "id")
    private Product product;

    // Derived from the associated Product object
    @Transient
    private String productName;

    private int quantity;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User u) {
        this.customer = u;
        if (customer != null) {
            this.customerEmail = customer.getEmailId();

        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product p) {
        this.product = p;
        if (product != null) {
            this.productName = product.getproductName();
        }
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
