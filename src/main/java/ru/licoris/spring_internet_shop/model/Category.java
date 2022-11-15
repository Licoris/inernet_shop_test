package ru.licoris.spring_internet_shop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "categories")
@Data
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @NotBlank(message = "field must be not blank")
    @Column(name = "category_name")
    private String categoryName;

    @Size(max = 150, message = "max number of characters 150")
    @Column(name = "description")
    private String description;

}
