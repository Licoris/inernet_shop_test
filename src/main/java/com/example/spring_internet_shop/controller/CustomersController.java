package com.example.spring_internet_shop.controller;

import com.example.spring_internet_shop.entity.Customer;
import com.example.spring_internet_shop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CustomersController {
    private final CustomerService customerService;


    @RequestMapping("/show-customers")
    public String showAllCustomers(Model model) {
        model.addAttribute("allCustomers", customerService.getAll());
        return "all-customers";
    }

    @RequestMapping("/save-customer")
    public String saveOrUpdateCustomer(@Valid @ModelAttribute(name = "customer") Customer customer,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "customer-info";
        }
        if (customer.getId() != null) {
            customerService.update(customer.getId(), customer);
            return "redirect:/show-customers";
        }
        customerService.save(customer);
        return "redirect:/show-customers";
    }

    @RequestMapping("/add-customer")
    private String addNewCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "/customer-info";
    }

    @RequestMapping("/update-customer")
    private String updateCustomer(@RequestParam(name = "id") Long id, Model model) {
        Customer customer = customerService.get(id);
        model.addAttribute("customer", customer);
        return "/customer-info";
    }
}
