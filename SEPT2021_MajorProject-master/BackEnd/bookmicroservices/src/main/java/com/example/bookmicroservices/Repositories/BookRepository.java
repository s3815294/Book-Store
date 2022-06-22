package com.example.bookmicroservices.Repositories;


import com.example.bookmicroservices.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookById(Long id);

    @Override
    List<Book> findAll();
    List<Book> findAllByTitleContaining(String title);
    List<Book> findAllByAuthorContaining(String Author);
    List<Book> findAllByIsbnContaining(String Isbn);
    @Query("Select b from Book b JOIN b.categories c where c.name LIKE %:name%")
    List<Book> findbylikeCategories(String name);
    @Query("Select b from Book b JOIN b.categories c where c.name = :name")
    List<Book> findbyexactCategories(String name);
}
