package com.example.electronicshop.models.enity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
@Data
@NoArgsConstructor
public class Product {
}
