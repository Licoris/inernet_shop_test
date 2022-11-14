package com.example.spring_internet_shop.repository;

import com.example.spring_internet_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
