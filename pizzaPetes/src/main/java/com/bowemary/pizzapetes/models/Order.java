package com.bowemary.pizzapetes.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;


    @ElementCollection
    @CollectionTable(name = "order_pizza_ids", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "pizza_id")
    private List<Long> pizzaIds;

    @Transient
    private List<Pizza> pizzas; 

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructor
    public Order() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

 
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(List<Long> pizzaIds) {
        this.pizzaIds = pizzaIds;
    }


    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
