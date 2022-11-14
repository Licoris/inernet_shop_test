package com.example.spring_internet_shop.rest_controller;

import com.example.spring_internet_shop.model.Customer;
import com.example.spring_internet_shop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomersRestController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> showAllCustomers() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public Customer showCustomer(@PathVariable Long id) {
        return customerService.get(id);
    }

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.save(customer);
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable(name = "id") Long id, @RequestBody Customer customer) {
        customerService.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
    }
}
