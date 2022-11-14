package com.example.spring_internet_shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @NotBlank(message = "product name has to be not null and not blank")
    @Column(name = "product_name")
    private String productName;

    @Min(value = 0, message = "units in stock has to be greater 0")
    @Column(name = "units_in_stock", columnDefinition = "int CHECK (units_in_stock >= 0)")
    private int unitsInStock = 0;

    @Min(value = 0, message = "price has to be greater 0")
    @Column(name = "unit_price")
    private double unitPrice = 0;

    @ManyToOne
    @NotNull(message = "field must be not null")
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String productName, int unitsInStock, double unitPrice) {
        this.productName = productName;
        this.unitsInStock = unitsInStock;
        this.unitPrice = unitPrice;
    }

}
