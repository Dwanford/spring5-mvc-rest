package com.dwanford.spring5mvcrest.controllers.v1;

import com.dwanford.spring5mvcrest.api.v1.model.CustomerDTO;
import com.dwanford.spring5mvcrest.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer", description = "This is Customer controller")
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "This lists all customers")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Operation(summary = "This creates new customer")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createNewCustomer(customerDTO);
   }

    @Operation(summary = "This returns customer by id value")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @Operation(summary = "This updates customer with new customer property")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.saveCustomerByDTO(id, customerDTO);
    }

    @Operation(summary = "This patching customer")
    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.patchCustomer(id,customerDTO);
    }

    @Operation(summary = "This deletes customer")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

}
