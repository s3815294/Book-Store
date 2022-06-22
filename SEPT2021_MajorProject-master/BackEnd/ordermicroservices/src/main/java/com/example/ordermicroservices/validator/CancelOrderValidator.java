package com.example.ordermicroservices.validator;

import com.example.ordermicroservices.model.SingleOrder;
import com.example.ordermicroservices.payload.OrderRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class CancelOrderValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return OrderRequest.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        SingleOrder order = (SingleOrder) object;
        Date cancelTime = new Date();

        long duration = order.getCreate_At().getTime() - cancelTime.getTime();
        long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
        long diffInMins = TimeUnit.MILLISECONDS.toMinutes(duration);
        if (diffInDays == 0) {
            if (diffInMins > 120) {
                errors.rejectValue("create_At","Value", "Must cancel order within 2 hours");
            }
        } else {
            errors.rejectValue("create_At","Value", "Must cancel order within 2 hours");
        }
    }
}
