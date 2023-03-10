package com.iontech.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.iontech.orderservice.model.Order;
// import com.iontech.orderservice.dto.OrderRequest;
import com.iontech.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Order order){
        String result = orderService.placeOrder(order);

        return result;
    }

    @GetMapping("test")
    public String test(){
        return "Hello there !  Order service !";
    }

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
    @GetMapping("inventory")
    public String callInventory(){
        return orderService.callInventory();
    }


}