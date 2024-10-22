package com.bowemary.pizzapetes.repositories;

import com.bowemary.pizzapetes.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findOrdersByUserId(Long userId);
}
