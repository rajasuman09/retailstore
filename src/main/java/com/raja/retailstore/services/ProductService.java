package com.raja.retailstore.services;

import com.raja.retailstore.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    Product addNewProduct(Product product);

    boolean deleteProduct(Long id);
}
