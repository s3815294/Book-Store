package com.example.bookmicroservices.Repositories;

import com.example.bookmicroservices.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String category);
}
