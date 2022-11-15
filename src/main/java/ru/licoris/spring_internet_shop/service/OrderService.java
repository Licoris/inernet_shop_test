package ru.licoris.spring_internet_shop.service;

import ru.licoris.spring_internet_shop.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order get(Long id);

    void save(Order order);

    void complete(Long id);

    void update(Long id, Order order);

    void delete(Long id);
}
