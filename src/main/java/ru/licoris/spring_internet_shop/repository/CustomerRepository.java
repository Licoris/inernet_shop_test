package ru.licoris.spring_internet_shop.repository;

import ru.licoris.spring_internet_shop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
