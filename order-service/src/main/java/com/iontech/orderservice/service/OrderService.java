package com.iontech.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
    

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        // Make a call to the inventory to check if product is in stock

        orderRepository.save(order);

        
    }

    public List<Order> getOrders(){
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