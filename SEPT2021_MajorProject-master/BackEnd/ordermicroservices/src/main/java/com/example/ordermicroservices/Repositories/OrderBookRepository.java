package com.example.ordermicroservices.Repositories;


import com.example.ordermicroservices.model.OrderBook;
import com.example.ordermicroservices.model.SingleOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderBookRepository extends CrudRepository<OrderBook, Long> {
    void removeOrderBookBySingleOrderId(Long id);
}
