package com.example.ordermicroservices.Repositories;


import com.example.ordermicroservices.model.OrderAccount;
import org.springframework.data.repository.CrudRepository;

public interface OrderAccountRepository extends CrudRepository<OrderAccount, Long> {
    OrderAccount getOrderAccountById(Long id);
}
