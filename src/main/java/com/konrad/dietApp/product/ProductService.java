package com.konrad.dietApp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    List<Product> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products;
    }

}
