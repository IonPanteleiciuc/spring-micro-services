package com.iontech.inventoryservice.service;

import com.iontech.inventoryservice.model.Inventory;
import com.iontech.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public Boolean isInStock(String skuCode) {

        try{
            Inventory item = inventoryRepository.findBySkuCode(skuCode).get();

        if (item.getQuantity() > 0) {
            return true;
        } else {
            return false;
        }
        } catch(Exception e) {
            throw new IllegalArgumentException("No such product in our store ! Check the skuCode");
        }
        

    }
}