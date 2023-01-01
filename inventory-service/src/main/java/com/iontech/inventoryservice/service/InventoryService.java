package com.iontech.inventoryservice.service;

import com.iontech.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public Boolean isInStock(String skuCode) {
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}