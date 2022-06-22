package com.example.bookmicroservices.Repositories;

import com.example.bookmicroservices.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
