package com.bowemary.pizzapetes.services;

import com.bowemary.pizzapetes.models.Order;
import com.bowemary.pizzapetes.models.OrderWithPizzas;
import com.bowemary.pizzapetes.models.Pizza;
import com.bowemary.pizzapetes.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaService pizzaService;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Pizza> getPizzasForOrder(Order order) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Long pizzaId : order.getPizzaIds()) {
            pizzaService.findPizzaById(pizzaId).ifPresent(pizzas::add);
        }
        return pizzas;
    }

    public List<OrderWithPizzas> getOrdersWithPizzasByUserId(Long userId) {
        List<Order> orders = findOrdersByUserId(userId);
        List<OrderWithPizzas> ordersWithPizzas = new ArrayList<>();

        for (Order order : orders) {
            List<Pizza> pizzas = getPizzasForOrder(order);
            ordersWithPizzas.add(new OrderWithPizzas(order, pizzas));
        }
        return ordersWithPizzas;
    }
}
