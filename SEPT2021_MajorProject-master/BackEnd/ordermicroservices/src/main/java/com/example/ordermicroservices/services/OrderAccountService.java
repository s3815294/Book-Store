package com.example.ordermicroservices.services;

import com.example.ordermicroservices.Repositories.OrderAccountRepository;
import com.example.ordermicroservices.Repositories.OrderBookRepository;
import com.example.ordermicroservices.Repositories.SingleOrderRepository;
import com.example.ordermicroservices.exceptions.OrderAlreadyExistsException;
import com.example.ordermicroservices.exceptions.OrderNotFoundException;
import com.example.ordermicroservices.model.OrderAccount;
import com.example.ordermicroservices.model.SingleOrder;
import com.example.ordermicroservices.payload.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAccountService {

    @Autowired
    OrderAccountRepository orderAccountRepository;


    public OrderAccount saveOrderAccount (OrderAccount orderAccount){
        try{
            return orderAccountRepository.save(orderAccount);
        } catch (Exception e){
            throw new OrderAlreadyExistsException("Account already exists");
        }
    }

    public OrderAccount findOrderAccount (Long id) {
        return orderAccountRepository.getOrderAccountById(id);
    }
}
