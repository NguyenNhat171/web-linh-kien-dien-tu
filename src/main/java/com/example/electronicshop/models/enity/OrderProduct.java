package com.example.electronicshop.models.enity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order_products")
@Getter
@Setter
@NoArgsConstructor
public class OrderProduct {
}
