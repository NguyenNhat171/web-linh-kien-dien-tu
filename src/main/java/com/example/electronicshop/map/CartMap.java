package com.example.electronicshop.map;

import com.example.electronicshop.communication.response.CartItemResponse;
import com.example.electronicshop.communication.response.CartResponse;
import com.example.electronicshop.models.enity.Order;
import com.example.electronicshop.models.enity.OrderProduct;

import java.util.stream.Collectors;

public class CartMap {
    public CartResponse toCartRes (Order order) {
        CartResponse res = new CartResponse(order.getId(), order.getTotalProduct(), order.getTotalPrice(), order.getState());
        res.setItems(order.getItems().stream().map(CartMap::toCartItemRes).collect(Collectors.toList()));
        return res;
    }

    public static CartItemResponse toCartItemRes(OrderProduct orderProduct) {
        return new CartItemResponse(orderProduct.getId(), orderProduct.getItem().getName(),
                orderProduct.getItem().getImages(),
                orderProduct.getItem().getPrice(),
                orderProduct.getQuantity());
    }
}
