package com.iontech.orderservice.service;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.iontech.orderservice.model.Order;
import com.iontech.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public String placeOrder(Order order) {

        final String skuCode = order.getSkuCode();

        // Make a call to the inventory to check if product is in stock
        Boolean result = webClient.get()
            .uri("http://164.68.113.115:8078/api/inventory/" + skuCode)
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();


        if(result){
            orderRepository.save(order);
            return "Yout order has been passed";
        } else {
            return "Product not in stock ! Try again later";
        }

    }

    public List<Order> getOrders(){
        return this.orderRepository.findAll();
    }

    public String callInventory(){
        String result = webClient.get()
            .uri("http://164.68.113.115:8078/api/inventory/test")
            .retrieve()
            .bodyToMono(String.class)
            .block();

        return result;
    }

}