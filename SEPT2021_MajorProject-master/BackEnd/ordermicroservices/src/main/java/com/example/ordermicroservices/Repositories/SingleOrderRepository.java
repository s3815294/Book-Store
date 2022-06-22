package com.example.ordermicroservices.Repositories;


import com.example.ordermicroservices.model.SingleOrder;
import com.example.ordermicroservices.utility.SingleOrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SingleOrderRepository extends CrudRepository<SingleOrder, Long> {
    SingleOrder findSingleOrderById(Long id);
    void removeSingleOrderById(Long id);
    List<SingleOrder> findSingleOrderByOrderAccount_UsernameAndAndSingleOrderStatus(String username, SingleOrderStatus status);

}
