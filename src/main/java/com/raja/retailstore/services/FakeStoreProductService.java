package com.raja.retailstore.services;

import com.raja.retailstore.dtos.FakeStoreProductDto;
import com.raja.retailstore.models.Category;
import com.raja.retailstore.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    RestTemplate restTemplate;

    @Autowired
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProduct) {
        Product product = new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());

        return product;
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
                );
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products",
                FakeStoreProductDto[].class);
        FakeStoreProductDto[] productArray = responseEntity.getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto productDto: productArray){
            products.add(convertFakeStoreProductToProduct(productDto));
        }
        return products;
    }

    @Override
    public List<Category> getAllCategories() {
        ResponseEntity<Category[]> responseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/categories",
                Category[].class
                );
        Category[] categoryArray = responseEntity.getBody();
        List<Category> categories = new ArrayList<>();
        for(Category category: categoryArray){
            categories.add(category);
        }
        return categories;
    }
}
