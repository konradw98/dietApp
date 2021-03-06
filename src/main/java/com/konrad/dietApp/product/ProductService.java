package com.konrad.dietApp.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    List<Product> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products;
    }

    public void delete(int id){
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(int id){
        return productRepository.findById(id);
    }

}
