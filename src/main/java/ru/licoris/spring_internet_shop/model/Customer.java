package ru.licoris.spring_internet_shop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    @Column(name = "surname")
    private String surname;

    @Size(min = 2, max = 50, message = "must be between 2 and 50 characters")
    @Column(name = "address")
    private String address;

    @Pattern(regexp = "^\\d{10}$", message = "phone must contain 10 numbers")
    @Column(name = "phone")
    private String phone;
}
