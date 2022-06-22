package com.example.bookmicroservices;

import com.example.bookmicroservices.services.CategoryService;
import com.example.bookmicroservices.Repositories.CategoryRepository;
import com.example.bookmicroservices.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class BookmicroservicesApplicationTests {

//    @Test
//    void contextLoads() {
//    }

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
            }
        };
    }

}
