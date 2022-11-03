package com.example.electronicshop.models.enity;

import com.example.electronicshop.models.provider.ESocial;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "product_elec")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductElec {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    @DocumentReference
    private Category category;
    private int quantity;
    private int sold;
    private double rate = 0;
    //    @DocumentReference(lookup="{'product':?#{#self._id} }", lazy = true)
//    private List<ProductImage> images = new ArrayList<>();
    private String url;
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime createdDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @LastModifiedDate
    LocalDateTime lastModifiedDate;

    public ProductElec(String name, String description, BigDecimal price, Category category, int quantity, int sold, double rate, String url, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.sold = sold;
        this.rate = rate;
        this.url = url;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
