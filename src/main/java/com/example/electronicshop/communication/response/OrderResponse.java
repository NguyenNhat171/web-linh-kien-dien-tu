package com.example.electronicshop.communication.response;

import com.example.electronicshop.models.enity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
public class OrderResponse {
    private String id;
    @DocumentReference
    private User user;
    private long totalProduct = 0;
    private BigDecimal totalPrice;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CartItemResponse> items = new ArrayList<>();
   private String address;
    private String state;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime createdDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime updateDate;

    public OrderResponse(String id, User user, long totalProduct, BigDecimal totalPrice, String address, String state, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.id = id;
        this.user = user;
        this.totalProduct = totalProduct;
        this.totalPrice = totalPrice;
        this.address = address;
        this.state = state;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }
}
