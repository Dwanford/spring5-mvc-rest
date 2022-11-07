package com.dwanford.spring5mvcrest.services;

import com.dwanford.spring5mvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerByLastName(String lastName);
}
