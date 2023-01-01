package com.iontech.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.iontech.productservice.dto.ProductRequest;
import com.iontech.productservice.model.Product;
import com.iontech.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public String helloWorld() {
        this.addInInventory();
        return "Hello there ! Product service";
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
    }

    public List<Product> listProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public void deleteProduct(String id) {

        Boolean isNotHere = productRepository.findById(id).isEmpty();
        if (isNotHere) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        productRepository.deleteById(id);
    }

    public Product getProduct(String id) {
        return productRepository.findById(id).get();
    }

    private void addInInventory() {
        final String uri = "http://164.68.113.115:8078/api/inventory/test";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }

}