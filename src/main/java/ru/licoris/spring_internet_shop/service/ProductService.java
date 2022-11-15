package ru.licoris.spring_internet_shop.service;

import ru.licoris.spring_internet_shop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    Product get(Long id);

    void save(Product product);

    void update(Long id, Product product);

    void delete(Long id);
}
