package com.iontech.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.iontech.orderservice.dto.OrderLineItemsDto;
import com.iontech.orderservice.dto.OrderRequest;
import com.iontech.orderservice.model.Order;
import com.iontech.orderservice.model.OrderLineItems;
import com.iontech.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        // Make a call to the inventory to check if product is in stock
        Boolean result = webClient.get()
            .uri("http://localhost:8078/api/inventory")
            .retrieve()
            .bodyToMono(Boolean.class)
            .block();


        if(result){
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product not in stock ! Try again later");
        }

    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}