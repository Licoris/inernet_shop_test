package com.example.spring_internet_shop.service;

import com.example.spring_internet_shop.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();

    Customer get(Long id);

    void save(Customer customer);

    void update(Long id, Customer customer);

    void delete(Long id);
}
