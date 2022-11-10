package com.example.spring_internet_shop.repository;

import com.example.spring_internet_shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
