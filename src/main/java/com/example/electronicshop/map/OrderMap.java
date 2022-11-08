package com.example.electronicshop.map;

import com.example.electronicshop.communication.response.OrderResponse;
import com.example.electronicshop.models.enity.Order;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMap {
    public OrderResponse toOrderRes (Order order) {
        return new OrderResponse(order.getId(),order.getUser(),
                order.getTotalProduct(), order.getTotalPrice(),order.getAddress() ,order.getState(),order.getCreatedDate(),order.getLastModifiedDate());
    }

    public OrderResponse toOrderDetailRes (Order order) {
        OrderResponse orderRes =  new OrderResponse(order.getId(),order.getUser(),
                order.getTotalProduct(), order.getTotalPrice(),order.getAddress() ,order.getState(),order.getCreatedDate(),order.getLastModifiedDate());;
        orderRes.setItems(order.getItems().stream().map(CartMap::toCartItemRes).collect(Collectors.toList()));
        return orderRes;
    }
}
