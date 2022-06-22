package com.example.ordermicroservices.services;

import com.example.ordermicroservices.Repositories.OrderBookRepository;
import com.example.ordermicroservices.exceptions.OrderAlreadyExistsException;
import com.example.ordermicroservices.exceptions.OrderNotFoundException;
import com.example.ordermicroservices.model.OrderBook;
import com.example.ordermicroservices.model.SingleOrder;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderBookService {

    @Autowired
    OrderBookRepository orderBookRepository;

    public OrderBook saveOrderBook (OrderBook book){
        try{
            return orderBookRepository.save(book);
        } catch (Exception e){
            throw new OrderAlreadyExistsException("OrderBook already exists");
        }
    }

    public void saveOrderBooks (List<OrderBook> books, SingleOrder order) {
       try {
           for(OrderBook book : books) {
               book.setSingleOrder(order);
           }
           orderBookRepository.saveAll(books);
       } catch (Exception e){
           throw new OrderAlreadyExistsException("OrderBook already exists");
       }
    }

    public void deleteBooksByOrder(SingleOrder order) {
        try {
            orderBookRepository.removeOrderBookBySingleOrderId(order.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



}
