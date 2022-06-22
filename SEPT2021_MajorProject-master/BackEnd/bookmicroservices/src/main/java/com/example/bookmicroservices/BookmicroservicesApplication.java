package com.example.bookmicroservices;

import com.example.bookmicroservices.services.CategoryService;
import com.example.bookmicroservices.Repositories.CategoryRepository;
import com.example.bookmicroservices.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.example.bookmicroservices.Repositories")
@SpringBootApplication
public class BookmicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookmicroservicesApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CategoryService categoryService, CategoryRepository categoryRepository) {
        return args -> {
            String[] categories = {"Fiction", "Non-Fiction", "Educational", "Free"};

            if (categoryRepository.count() == 0) {
                for (int i = 0; i < categories.length; i++) {
                    Category category = new Category();
                    category.setName(categories[i]);
                    categoryService.saveCategory(category);
                }
                System.out.println("Finished seeding categories");
            }
        };
    }
}
