package com.iontech.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iontech.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}