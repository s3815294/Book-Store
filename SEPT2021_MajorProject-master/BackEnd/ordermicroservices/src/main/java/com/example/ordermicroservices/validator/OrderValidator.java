package com.example.ordermicroservices.validator;

import com.example.ordermicroservices.model.OrderBook;
import com.example.ordermicroservices.payload.OrderBookRequest;
import com.example.ordermicroservices.payload.OrderRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

// validates orders to ensure they do not have negative price books.
@Component
public class OrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        OrderRequest order = (OrderRequest) object;
        for (OrderBookRequest book : order.getBooksOrdered()) {

            if (book.getPrice() == null) {
                errors.rejectValue("booksOrdered","Value", "Price must be greater or equal to 0.");
            } else if (book.getPrice().compareTo(BigDecimal.ZERO) == -1) {
                errors.rejectValue("booksOrdered","Value", "Price must be greater or equal to 0.");
            }
        }
    }
}
