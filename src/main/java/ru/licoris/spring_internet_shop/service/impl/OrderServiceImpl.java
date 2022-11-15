package ru.licoris.spring_internet_shop.service.impl;

import ru.licoris.spring_internet_shop.model.Order;
import ru.licoris.spring_internet_shop.model.OrderItem;
import ru.licoris.spring_internet_shop.model.Product;
import ru.licoris.spring_internet_shop.exception.BadRequestException;
import ru.licoris.spring_internet_shop.exception.ResourceNotFoundException;
import ru.licoris.spring_internet_shop.repository.OrderRepository;
import ru.licoris.spring_internet_shop.service.OrderService;
import ru.licoris.spring_internet_shop.service.enums.ErrorsMessageEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final Logger orderLogger = LogManager.getLogger("orderLogger");

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order get(Long id) {
        return orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
    }


    @Override
    public void save(Order order) {
        if (order.getId() != null) {
            throw new BadRequestException(ErrorsMessageEnum.BAD_POST_ID_REQUEST.getMessage());
        }
        if (order.getStatus().equals("paid")) {
            throw new BadRequestException(ErrorsMessageEnum.STATUS_ALREADY_PAID.getMessage());
        }

        orderRepository.save(order);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void complete(Long id) {
        var order = orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
        orderLogger.warn("User {} is completing a order {}", order.getCustomer().getId(), id);
        completeOrder(order);
        orderLogger.warn("Order {} completed", id);
    }


    private void completeOrder(Order order) {
        order.setOrderDate(LocalDate.now());
        order.setStatus("paid");

        List<OrderItem> orderItem = order.getOrderItems();

        for (OrderItem item : orderItem) {
            Product product = item.getProduct();
            product.setUnitsInStock(product.getUnitsInStock() - item.getQuantity());
        }
    }

    @Override
    public void update(Long id, Order receivedOrder) {
        var currentOrder = orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));

        updateOrder(currentOrder, receivedOrder.getOrderItems());
        orderRepository.save(currentOrder);
    }

    private void updateOrder(Order curOrder, List<OrderItem> items) {
        curOrder.getOrderItems().clear();
        curOrder.getOrderItems().addAll(ofNullable(items).orElse(emptyList()));
    }


    @Override
    public void delete(Long id) {
        orderRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));

        orderRepository.deleteById(id);
    }
}
