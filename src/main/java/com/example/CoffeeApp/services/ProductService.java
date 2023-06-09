package com.example.CoffeeApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.CoffeeApp.repositories.ProductRepo;
import com.example.CoffeeApp.domains.Product;

/* Service class responsible for product-related operations. Implements business logic for product management and interacts with ProductRepo for data access */

@Service
public class ProductService {
    private final ProductRepo productrepo;

    public ProductService(ProductRepo productRepo) {
        this.productrepo = productRepo;
    }

    // Adds a new product to the repository
    public void addProduct(Product p) {
        int id = Math.abs(p.getproductName().hashCode());
        p.setid(id);
        productrepo.save(p);
    }

    // Retrieves a list of all products
    public List<Product> viewProducts() {
        return (List<Product>) productrepo.findAll();
    }

    // Updates an existing product in the repository
    public String updateProduct(Product p) {
        productrepo.save(p);
        return "Product" + p.getproductName() + " " + "with id:" + " " + p.getid() + " " + "is updated sucessfully.";
    }

    // Deletes a product from the repository based on its ID
    public boolean deleteProduct(Long id) {
        if (productrepo.existsById(id)) {
            productrepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    // Checks if a product with the given ID exists in the repository
    public boolean getid(long id) {
        return productrepo.existsById(id);
    }

    // Checks if a product with the given name already exists in the repository
    public boolean getpName(String pName) {
        List<Product> products = viewProducts();
        for (Product product : products) {
            if (product.getproductName().equals(pName)) {
                return false;
            }

        }

        return true;
    }

    public Product findById(Long id) {
        Optional<Product> optionalProduct = productrepo.findById(id);
        return optionalProduct.orElse(null);
    }

    public boolean existsById(long getid) {
        return productrepo.existsById(getid);
    }

}
