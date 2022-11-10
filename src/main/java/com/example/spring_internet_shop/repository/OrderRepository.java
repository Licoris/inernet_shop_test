package com.example.spring_internet_shop.repository;

import com.example.spring_internet_shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
