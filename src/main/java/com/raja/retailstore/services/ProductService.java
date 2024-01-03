package com.raja.retailstore.services;

import com.raja.retailstore.models.Category;
import com.raja.retailstore.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    List<Category> getAllCategories();
}
