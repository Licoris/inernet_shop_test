package com.example.spring_internet_shop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
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

    public Customer(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }
}
