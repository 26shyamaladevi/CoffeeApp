package com.example.CoffeeApp.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.CoffeeApp.repositories.ProductRepo;
import com.example.CoffeeApp.domains.Product;

@Service
public class ProductService {
    private final ProductRepo productrepo;

    public ProductService(ProductRepo productRepo) {
        this.productrepo = productRepo;
    }

    public void addProduct(Product p) {
        productrepo.save(p);
    }

    public List<Product> viewProducts() {

        return (List<Product>) productrepo.findAll();

    }

    public String updateProduct(Product p) {

        productrepo.save(p);
        return "Product" + p.getproductName() + " " + "with id:" + " " + p.getid() + " " + "is updated sucessfully.";
    }

    public boolean deleteProduct(Long id) {
        if (productrepo.existsById(id)) {
            productrepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean getid(long id) {
        return productrepo.existsById(id);
    }

    public boolean getpName(String pName) {
        List<Product> products = viewProducts();
        for (Product product : products) {
            if (product.getproductName().equals(pName)) {
                return false;
            }

        }

        return true;
    }

}
