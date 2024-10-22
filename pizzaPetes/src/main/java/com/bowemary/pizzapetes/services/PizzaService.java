package com.bowemary.pizzapetes.services;

import com.bowemary.pizzapetes.models.Pizza;
import com.bowemary.pizzapetes.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return (List<Pizza>) pizzaRepository.findAll();
    }

    public Optional<Pizza> findPizzaById(Long id) {
        return pizzaRepository.findById(id);
    }

    public void savePizza(Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    public void deletePizza(Long id) {
        pizzaRepository.deleteById(id);
    }

    public List<String> getAvailableToppings() {
        return Arrays.asList("Pepperoni", "Mushrooms", "Onions", "Sausage", "Bacon",
                             "Extra cheese", "Black olives", "Green peppers",
                             "Pineapple", "Spinach");
    }

    public boolean existsById(Long id) {
        return pizzaRepository.existsById(id);
    }
}
