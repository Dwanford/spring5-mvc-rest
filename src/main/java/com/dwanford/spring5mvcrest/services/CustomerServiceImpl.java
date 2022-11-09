package com.dwanford.spring5mvcrest.services;

import com.dwanford.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.dwanford.spring5mvcrest.api.v1.model.CustomerDTO;
import com.dwanford.spring5mvcrest.domain.Customer;
import com.dwanford.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.toDTO(customerRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public CustomerDTO getCustomerByLastName(String lastName) {
        return customerMapper.toDTO(customerRepository.findByLastName(lastName));
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.toEntity(customerDTO));
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setId(id);

        return saveAndReturnDTO(customer);
    }

    public CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer saved = customerRepository.save(customer);
        return customerMapper.toDTO(saved);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if(customerDTO.getFirstName() != null)
                customer.setFirstName(customerDTO.getFirstName());
            if(customerDTO.getLastName() != null)
                customer.setLastName(customerDTO.getLastName());

            return customerMapper.toDTO(customerRepository.save(customer));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if(customerRepository.findById(id).isPresent())
            customerRepository.deleteById(id);
        else throw new ResourceNotFoundException();
    }
}
