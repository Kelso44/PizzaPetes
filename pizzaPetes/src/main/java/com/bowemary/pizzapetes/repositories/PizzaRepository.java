package com.bowemary.pizzapetes.repositories;

import com.bowemary.pizzapetes.models.Pizza;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, Long> {
}
