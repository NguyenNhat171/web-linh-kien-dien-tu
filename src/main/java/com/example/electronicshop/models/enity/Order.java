package com.example.electronicshop.models.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    @DocumentReference(lazy = true)
    @JsonIgnore
    private User user;

    @ReadOnlyProperty
    @DocumentReference(lookup = "{'order':?#{#self._id} }", lazy = true)
    private List<OrderProduct> productElecList;
    private String address;
    private String amount;
    private String state;
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime createdDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @LastModifiedDate
    LocalDateTime lastModifiedDate;
    @Transient
    private long totalProduct ;
    @Transient
    private BigDecimal totalPrice;


    public long getTotalProduct() {
        return productElecList.size();
    }
    public  BigDecimal getTotalPrice() {
        totalPrice = productElecList.stream().map(OrderProduct::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice;
    }

    public Order(User user, String state) {
        this.user = user;
        this.state = state;
    }
}
