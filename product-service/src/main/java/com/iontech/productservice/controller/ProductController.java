package com.iontech.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.iontech.productservice.dto.ProductRequest;
import com.iontech.productservice.model.Product;
import com.iontech.productservice.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    public ProductService productService;

    @GetMapping("/test")
    public String helloWorld() {
        return productService.helloWorld();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping("/list")
    public List<Product> getProducts() {
        return productService.listProducts();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/get/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

}
