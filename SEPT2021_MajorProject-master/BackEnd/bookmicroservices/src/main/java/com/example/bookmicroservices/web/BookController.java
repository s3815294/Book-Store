package com.example.bookmicroservices.web;


import com.example.bookmicroservices.model.Review;
import com.example.bookmicroservices.payload.BookRequest;
import com.example.bookmicroservices.model.Book;
import com.example.bookmicroservices.payload.SearchRequest;
import com.example.bookmicroservices.services.*;
import com.example.bookmicroservices.validator.BookValidator;
import com.example.bookmicroservices.validator.ReviewValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookValidator bookValidator;

    @Autowired
    private ReviewValidator reviewValidator;
    @Autowired
    private AWSS3Service awsS3Service;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<?> upload(@RequestPart("book") @Valid BookRequest book,
                                    @RequestPart(name = "image", required = false) MultipartFile image,
                                    @RequestPart(name = "pdf", required = false) MultipartFile pdf, BindingResult result) {
        // validate the book
        bookValidator.validate(book,result);
        if (image == null || pdf == null) {
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("file", "Book must have an image and pdf.");
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        // book is valid add the book.
        Book newBook = bookService.saveBook(book, image);
        // upload the file to s3
        String bookURL = awsS3Service.uploadFile(image, newBook.getId(), awsS3Service.BOOK_IMAGE);
        String pdfURL = awsS3Service.uploadFile(pdf, newBook.getId(), awsS3Service.BOOK_PDF);
        // if the image fails to upload remove the book from the database
        if (bookURL == null) {
            bookService.removeBookById(newBook.getId());
            return new ResponseEntity<>("Book has failed to be added, image upload error", HttpStatus.BAD_REQUEST);
        }
        // if the image fails to upload remove the book from the database
        if (pdfURL == null) {
            bookService.removeBookById(newBook.getId());
            return new ResponseEntity<>("Book has failed to be added, pdf upload error", HttpStatus.BAD_REQUEST);
        }
        // book was succesfully added and image uploaded
        return  new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getById(@Valid @RequestParam Long id){
        // gets book by id and returns book, badrequest if not found.
        BookRequest book = bookService.getBookById(id);
        if (book == null) {
            return new ResponseEntity<>("Book not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        // gets all books in the system.
        List<BookRequest> books = bookService.getAllBooks();
        if (books.size() == 0) {
            return new ResponseEntity<>("No books available in that category", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<?> booksByCategory(@Valid @RequestParam String category){
        // gets a book by its category.
        List<BookRequest> books = bookService.getBooksByCategory(category);
        if (books.size() == 0) {
            return new ResponseEntity<>("No books available in that category", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBook(@Valid @RequestParam(required=false) String title,
                                        @RequestParam(required=false) String author,
                                        @RequestParam(required=false) String isbn,
                                        @RequestParam(required=false) String category){
        // create search request object. And returns a book by either of the fields passed in if it matches.
        SearchRequest searchRequest = new SearchRequest(title, author, isbn, category);
        List<BookRequest> books = bookService.getBookBySearch(searchRequest);
        if (books.size() == 0) {
            return new ResponseEntity<>("No book matches that search.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getBookCategories(){
        // Returns all the categories available that a book can have.
        List<String> categories = categoryService.getBookCategories();
        if (categories.size() == 0) {
            return new ResponseEntity<>("No Categories Exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(value = "/id", method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public ResponseEntity<?> update(@RequestPart("book") @Valid BookRequest book,
                                    @RequestPart(name = "image", required = false) MultipartFile image,
                                    @RequestPart(name = "pdf", required = false) MultipartFile pdf, BindingResult result) {

        // validate the book
        bookValidator.validate(book,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        // book is valid add the book.
        Book updateBook = bookService.update(book, image);
        if(image != null) {
            String publicURL = awsS3Service.uploadFile(image, book.getId(), awsS3Service.BOOK_IMAGE);
            if (publicURL == null) {
                return new ResponseEntity<>("Book image failed to upload", HttpStatus.BAD_REQUEST);
            }
        }
        if (pdf != null) {
            String publicURL = awsS3Service.uploadFile(pdf, book.getId(), awsS3Service.BOOK_PDF);
            if (publicURL == null) {
                return new ResponseEntity<>("Book pdf contents failed to upload", HttpStatus.BAD_REQUEST);
            }
        }
        return  new ResponseEntity<>(updateBook, HttpStatus.CREATED);
    }

    // this method accepts a review and adds it to a book.
    @PostMapping(value = "/review")
    public ResponseEntity<?> addReview(@RequestBody Review review, BindingResult result) {
        // validate the book
        reviewValidator.validate(review,result);
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;
        // book is valid add the book.
        Review newBook = reviewService.saveReview(review);
        return  new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }
}
