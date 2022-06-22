package com.example.bookmicroservices.services;

import com.example.bookmicroservices.Repositories.BookRepository;
import com.example.bookmicroservices.Repositories.CategoryRepository;
import com.example.bookmicroservices.exceptions.BookAlreadyExistsException;
import com.example.bookmicroservices.exceptions.BookNotFoundException;
import com.example.bookmicroservices.model.Book;
import com.example.bookmicroservices.model.Category;
import com.example.bookmicroservices.payload.BookRequest;
import com.example.bookmicroservices.payload.SearchRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Book saveBook (BookRequest book, MultipartFile file){
        try{
            Book newBook = new Book();
            // get file extension for S3 url.
            String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(book.getAuthor());
            newBook.setIsbn(book.getIsbn());
            newBook.setPrice(book.getPrice());
            newBook.setBookCondition(book.getBookCondition());
            newBook.setSellerId(book.getSellerId());
            newBook.setSellerName(book.getSellerName());
            newBook.setImageType(filenameExtension);
            Set<Category> bookcategory = new HashSet<>();
            for(int i = 0; i < book.getCategories().size(); i++) {
                bookcategory.add(categoryRepository.findByName(book.getCategories().get(i)));
            }
            newBook.setCategories(bookcategory);
            return bookRepository.save(newBook);
        } catch (Exception e){
            throw new BookAlreadyExistsException("Book '"+ book.getTitle()+"' already exists");
        }

    }

    public List<BookRequest> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return convertBookClass(books);
        } catch (Exception e) {
            throw new BookNotFoundException("Books not found");
        }
    }
    public BookRequest getBookById (long id) {
        try{
            return new BookRequest(bookRepository.findBookById(id));
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new BookNotFoundException("Book by id: " + id + " does not exist");
        }
    }

    public List<BookRequest> getBooksByCategory(String category) {
       try {
            List<Book> books = bookRepository.findbyexactCategories(category);
            return convertBookClass(books);
        } catch (Exception e) {
            throw new BookNotFoundException("Book not found");
        }
    }
    public List<BookRequest> getBookBySearch (SearchRequest searchRequest) {
        List<Book> books = null;
        try {
            if (searchRequest.getTitle() != null) {
                books = bookRepository.findAllByTitleContaining(searchRequest.getTitle());
            } else if (searchRequest.getAuthor() != null) {
                books = bookRepository.findAllByAuthorContaining(searchRequest.getAuthor());
            } else if (searchRequest.getIsbn() != null) {
                books = bookRepository.findAllByIsbnContaining(searchRequest.getIsbn());
            } else if (searchRequest.getCategory() != null) {
                books = bookRepository.findbylikeCategories(searchRequest.getCategory());
            }
            return convertBookClass(books);
        } catch (Exception e) {
            throw new BookNotFoundException("Book not found");
        }
    }

    public void removeBookById(Long id) {
        try {
            bookRepository.deleteById(id);
        } catch (Exception e){
            throw new BookNotFoundException("Book does not exist");
        }
    }

//    converts a books to bookRequest as hashset is not viable in json.
    private List<BookRequest> convertBookClass(List<Book> books) {
        List<BookRequest> bookReturn = new ArrayList<>();
        if (books.size() != 0) {
            for (int i = 0; i < books.size(); i++) {
                bookReturn.add(new BookRequest(books.get(i)));
            }
        }
        return bookReturn;
    }

    public Book update(BookRequest book, MultipartFile file) {
        try{
            Book newBook = bookRepository.findBookById(book.getId());
            if (file != null) {
                String filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
                newBook.setImageType(filenameExtension);
            }
            newBook.setTitle(book.getTitle());
            newBook.setAuthor(book.getAuthor());
            newBook.setIsbn(book.getIsbn());
            newBook.setPrice(book.getPrice());
            newBook.setBookCondition(book.getBookCondition());
            Set<Category> bookcategory = new HashSet<>();
            for(int i = 0; i < book.getCategories().size(); i++) {
                bookcategory.add(categoryRepository.findByName(book.getCategories().get(i)));
            }
            newBook.setCategories(bookcategory);

            return bookRepository.save(newBook);
        } catch (Exception e) {
            throw new BookNotFoundException("Book not found");
        }
    }
}
