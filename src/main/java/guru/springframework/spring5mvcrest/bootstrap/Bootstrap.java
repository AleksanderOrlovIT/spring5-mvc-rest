package guru.springframework.spring5mvcrest.bootstrap;

import guru.springframework.spring5mvcrest.domain.Category;
import guru.springframework.spring5mvcrest.domain.Customer;
import guru.springframework.spring5mvcrest.repository.CategoryRepository;
import guru.springframework.spring5mvcrest.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
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
        freddy.setFirstName("Freddy");
        freddy.setLastName("Meyers");

        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Buck");

        Customer micheal = new Customer();
        micheal.setFirstName("Micheal");
        micheal.setLastName("Weston");

        Customer micheal2 = new Customer();
        micheal2.setFirstName("Micheal 2");
        micheal2.setLastName("Weston 2");

        customerRepository.save(freddy);
        customerRepository.save(joe);
        customerRepository.save(micheal);
        customerRepository.save(micheal2);
        System.out.println("Customers loaded");
    }
}
