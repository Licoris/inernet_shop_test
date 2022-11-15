package ru.licoris.spring_internet_shop.rest_controller;

import ru.licoris.spring_internet_shop.model.Order;
import ru.licoris.spring_internet_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersRestController {
    private final OrderService orderService;


    @GetMapping
    public List<Order> showAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public Order showOrder(@PathVariable Long id) {
        return orderService.get(id);
    }

    @PostMapping
    public void saveOrder(@RequestBody Order order) {
        orderService.save(order);
    }

    @PutMapping("/{id}/complete")
    private void completeOrder(@PathVariable(name = "id") Long id) {
        orderService.complete(id);
    }


    @PutMapping("/{id}")
    public void updateOrder(@PathVariable(name = "id") Long orderId, @RequestBody Order order) {
        orderService.update(orderId, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
    }
}
