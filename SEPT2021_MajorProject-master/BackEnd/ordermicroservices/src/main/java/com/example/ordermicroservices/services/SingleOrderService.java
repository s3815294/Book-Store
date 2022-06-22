package com.example.ordermicroservices.services;

import com.example.ordermicroservices.Repositories.OrderAccountRepository;
import com.example.ordermicroservices.Repositories.OrderBookRepository;
import com.example.ordermicroservices.Repositories.SingleOrderRepository;
import com.example.ordermicroservices.exceptions.OrderAlreadyExistsException;
import com.example.ordermicroservices.exceptions.OrderNotFoundException;

import com.example.ordermicroservices.model.OrderAccount;
import com.example.ordermicroservices.model.OrderBook;
import com.example.ordermicroservices.model.SingleOrder;
import com.example.ordermicroservices.payload.OrderBookRequest;
import com.example.ordermicroservices.payload.OrderRequest;
import com.example.ordermicroservices.utility.SingleOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SingleOrderService {

    @Autowired
    SingleOrderRepository singleOrderRepository;
    @Autowired
    OrderAccountService orderAccountService;
    @Autowired
    OrderBookService orderBookService;

    public SingleOrder saveOrder (OrderRequest orderRequest){
        try{
            SingleOrder newOrder = new SingleOrder();
            newOrder.setSingleOrderStatus(SingleOrderStatus.PENDING);
            // Add books to the order and calculate total
            Set<OrderBook> booksAdded = new HashSet<>();
            BigDecimal total = BigDecimal.ZERO;
            List<OrderBook> books = convertBookPayload(orderRequest.getBooksOrdered());
            newOrder.setOrderBooks(books);
            newOrder.setOrderTotal(books);
            // check if account exists and add it if it doesn't
            OrderAccount account = orderAccountService.findOrderAccount(orderRequest.getUserId());
            if (account == null) {
                account = orderAccountService.saveOrderAccount(new OrderAccount(orderRequest.getUserId(), orderRequest.getUsername()));
            }
            newOrder.setOrderAccount(account);
            // save order
            SingleOrder order = singleOrderRepository.save(newOrder);
            // save books and assign order id to books.
            orderBookService.saveOrderBooks(books, order);
            return order;
        } catch (Exception e){
            System.out.println(e.getMessage());
            throw new OrderAlreadyExistsException("Order already exists");
        }

    }

    public SingleOrder getOrderById(Long id) {
        try {
            return singleOrderRepository.findSingleOrderById(id);
        } catch (Exception e) {
            throw new OrderNotFoundException("Orders for id " + id + " do not exist.");
        }
    }

    @Transactional
    public SingleOrder removeOrderById(SingleOrder order) {
        try {
            singleOrderRepository.removeSingleOrderById(order.getId());
            return order;
        } catch (Exception e) {
            throw new OrderNotFoundException("Orders for id " + order.getId() + " do not exist.");
        }
    }

    public List<SingleOrder> getOrdersByUsernamePending(String username) {
        try {
            return singleOrderRepository.findSingleOrderByOrderAccount_UsernameAndAndSingleOrderStatus(username, SingleOrderStatus.PENDING);
        } catch (Exception e) {
            throw new OrderNotFoundException("Orders for username " + username + " do not exist.");
        }
    }

    public List<SingleOrder> getAllOrders() {
        try {
            return (List<SingleOrder>) singleOrderRepository.findAll();
        } catch (Exception e) {
            throw new OrderNotFoundException("No orders exist.");
        }
    }

//    converts a list of orderbookrequests to orderbooks.
    private List<OrderBook> convertBookPayload(List<OrderBookRequest> orderBookRequests) {
        List<OrderBook> orderBooks = new ArrayList<>();
        for(OrderBookRequest request : orderBookRequests) {
            orderBooks.add(new OrderBook(request));
        }
        return orderBooks;
    }
}
