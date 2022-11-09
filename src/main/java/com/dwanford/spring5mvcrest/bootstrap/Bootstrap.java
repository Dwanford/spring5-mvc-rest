package com.dwanford.spring5mvcrest.bootstrap;

import com.dwanford.spring5mvcrest.domain.Category;
import com.dwanford.spring5mvcrest.domain.Customer;
import com.dwanford.spring5mvcrest.domain.Vendor;
import com.dwanford.spring5mvcrest.repositories.CategoryRepository;
import com.dwanford.spring5mvcrest.repositories.CustomerRepository;
import com.dwanford.spring5mvcrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
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
        System.out.println("Category data loaded  = " + categoryRepository.count());
    }

    private void loadCustomers() {
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
        System.out.println("Customer data loaded  = " + customerRepository.count());
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Pierce & Pierce");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Lou Bloom");

        vendorRepository.saveAll(Arrays.asList(vendor1, vendor2));
        System.out.println("Vendor data loaded  = " + vendorRepository.count());
    }
}
