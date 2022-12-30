package com.iontech.inventoryservice.controller;

import com.iontech.inventoryservice.dto.InventoryResponse;
import com.iontech.inventoryservice.model.Inventory;
import com.iontech.inventoryservice.repository.InventoryRepository;
import com.iontech.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    @Autowired
    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping("db")
    public List<Inventory> test(){
        return inventoryRepository.findAll();
        // return "Hello there ! It works !";
    }

    @GetMapping("test")
    public String hello(){
        // return inventoryRepository.findAll();
        return "Hello there ! It works !";
    }
}