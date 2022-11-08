package com.dwanford.spring5mvcrest.services;

import com.dwanford.spring5mvcrest.api.v1.model.CustomerDTO;
import com.dwanford.spring5mvcrest.domain.Customer;
import com.dwanford.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    private static final Long ID = 2L;
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";

    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();
        assertEquals(3, customerDTOS.size());
    }

    @Test
    void getCustomerByLastName() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findByLastName(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByLastName(LAST_NAME);
        assertEquals(FIRST_NAME, customerDTO.getFirstName());
        assertEquals(LAST_NAME, customerDTO.getLastName());
    }

    @Test
    void createNewCustomer() {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
    }

    @Test
    void saveCustomerByDTO() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(FIRST_NAME);

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("/api/v1/customers/"+ ID, savedDto.getCustomerUrl());
    }
}