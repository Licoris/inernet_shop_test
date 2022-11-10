package com.example.spring_internet_shop.repository;

import com.example.spring_internet_shop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
