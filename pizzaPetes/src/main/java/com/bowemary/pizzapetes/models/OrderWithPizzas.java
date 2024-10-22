package com.bowemary.pizzapetes.models;

import java.util.List;

public class OrderWithPizzas {
    private Order order;
    private List<Pizza> pizzas;

    public OrderWithPizzas(Order order, List<Pizza> pizzas) {
        this.order = order;
        this.pizzas = pizzas;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
