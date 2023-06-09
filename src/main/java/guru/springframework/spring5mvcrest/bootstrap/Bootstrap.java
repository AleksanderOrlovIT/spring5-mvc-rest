package guru.springframework.spring5mvcrest.bootstrap;

import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.domain.Vendor;
import guru.springframework.spring5mvcrest.repository.CategoryRepository;
import guru.springframework.spring5mvcrest.repository.CustomerRepository;
import guru.springframework.spring5mvcrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

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

    public void loadCategories(){
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded");
    }

    public void loadCustomers(){
        Customer freddy = new Customer();
        freddy.setFirstname("Freddy");
        freddy.setLastname("Meyers");

        Customer joe = new Customer();
        joe.setFirstname("Joe");
        joe.setLastname("Buck");

        Customer micheal = new Customer();
        micheal.setFirstname("Micheal");
        micheal.setLastname("Weston");

        Customer micheal2 = new Customer();
        micheal2.setFirstname("Micheal 2");
        micheal2.setLastname("Weston 2");

        customerRepository.save(freddy);
        customerRepository.save(joe);
        customerRepository.save(micheal);
        customerRepository.save(micheal2);
        System.out.println("Customers loaded");
    }

    public void loadVendors(){
        Vendor vendor1 = new Vendor();
        vendor1.setName("First Vendor");

        Vendor vendor2 = new Vendor();
        vendor2.setName("Second Vendor");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
    }
}
