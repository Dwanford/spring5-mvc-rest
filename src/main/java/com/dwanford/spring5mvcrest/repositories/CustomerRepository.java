package com.dwanford.spring5mvcrest.repositories;

import com.dwanford.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByLastName(String lastName);
}
