package com.bowemary.pizzapetes.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    private String crust; 

    @ElementCollection
    private List<String> toppings;

    private double price; 

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Pizza() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust; 
    }

    public void setCrust(String crust) {
        this.crust = crust; 
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // method to create a name based on size, crust, and toppings
    public String getName() {
        StringBuilder name = new StringBuilder(size + " " + crust + " Pizza");
        if (toppings != null && !toppings.isEmpty()) {
            name.append(" with ");
            for (int i = 0; i < toppings.size(); i++) {
                name.append(toppings.get(i));
                if (i < toppings.size() - 1) {
                    name.append(", "); 
                }
            }
        } else {
            name.append(" (no toppings)"); // Show this if no toppings are selected
        }
        return name.toString();
    }
}
