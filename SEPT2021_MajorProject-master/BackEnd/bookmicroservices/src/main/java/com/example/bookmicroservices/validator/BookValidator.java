
package com.example.bookmicroservices.validator;

import com.example.bookmicroservices.payload.BookRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Validates a book to ensure it has valid categories and price.
@Component
public class BookValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BookRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        // categories hard-coded as autowired fails on tests.
        List<String> categories = new ArrayList<>();
        categories.add("Fiction");
        categories.add("Educational");
        categories.add("Non-Fiction");
        categories.add("Free");
        BookRequest book = (BookRequest) object;

        if (book.getCategories().size() == 0) {
            errors.rejectValue("categories","Value", "Categories picked are invalid.");
        } else if (!categories.containsAll(book.getCategories())) {
            errors.rejectValue("categories","Value", "Categories picked are invalid.");
        }

        if (book.getBookCondition() == null) {
            errors.rejectValue("bookCondition","Value", "Book condition must be NEW or USED");
        }

        if (book.getPrice() == null) {
            errors.rejectValue("price","Value", "Price must be greater or equal to 0.");
        } else if (book.getPrice().compareTo(BigDecimal.ZERO) == -1) {
            errors.rejectValue("price","Value", "Price must be greater or equal to 0.");
        }
    }
}
