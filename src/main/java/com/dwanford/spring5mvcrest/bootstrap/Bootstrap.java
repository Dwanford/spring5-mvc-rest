package com.dwanford.spring5mvcrest.bootstrap;

import com.dwanford.spring5mvcrest.domain.Category;
import com.dwanford.spring5mvcrest.domain.Customer;
import com.dwanford.spring5mvcrest.repositories.CategoryRepository;
import com.dwanford.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh  = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");


        categoryRepository.saveAll(Arrays.asList(fruits, dried, fresh, exotic, nuts));

        Customer johnDoe = new Customer();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");

        Customer patrickBateman = new Customer();
        patrickBateman.setFirstName("Patrick");
        patrickBateman.setLastName("Bateman");

        Customer travisBickle = new Customer();
        travisBickle.setFirstName("Travis");
        travisBickle.setLastName("Bickle");

        customerRepository.saveAll(Arrays.asList(johnDoe, patrickBateman, travisBickle));

        System.out.println("Category data loaded  = " + categoryRepository.count());
        System.out.println("Customer data loaded  = " + customerRepository.count());
    }
}
