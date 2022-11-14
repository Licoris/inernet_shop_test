package com.example.spring_internet_shop.service;

import com.example.spring_internet_shop.model.Customer;
import com.example.spring_internet_shop.exception.BadRequestException;
import com.example.spring_internet_shop.exception.ResourceNotFoundException;
import com.example.spring_internet_shop.repository.CustomerRepository;
import com.example.spring_internet_shop.service.enums.ErrorsMessageEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer get(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
    }

    @Override
    public void save(Customer customer) {
        if (customer.getId() != null) {
            throw new BadRequestException(ErrorsMessageEnum.BAD_POST_ID_REQUEST.getMessage());
        }
        customerRepository.save(customer);
    }

    public void update(Long id, Customer receivedCustomer) {
        var currentCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
        updateCustomer(currentCustomer, receivedCustomer);

        customerRepository.save(currentCustomer);
    }

    private void updateCustomer(Customer cur, Customer recCustomer) {
        cur.setName(recCustomer.getName());
        cur.setAddress(recCustomer.getAddress());
        cur.setSurname(recCustomer.getSurname());
        cur.setPhone(recCustomer.getPhone());
    }

    @Override
    public void delete(Long id) {
        customerRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorsMessageEnum.NOT_FOUND.getMessage()));
        customerRepository.deleteById(id);
    }
}
