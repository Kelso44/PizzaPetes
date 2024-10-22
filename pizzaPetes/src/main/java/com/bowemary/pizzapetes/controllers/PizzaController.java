package com.bowemary.pizzapetes.controllers;

import com.bowemary.pizzapetes.models.Pizza;
import com.bowemary.pizzapetes.services.PizzaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    // Show form to create a new pizza
    @GetMapping("/new")
    public String createPizzaForm(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("availableToppings", pizzaService.getAvailableToppings());
        return "pizzaForm"; 
    }


    @PostMapping
    public String createPizza(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("availableToppings", pizzaService.getAvailableToppings());
            return "pizzaForm";  // back to the form if there are validation errors
        }

        switch (pizza.getSize()) {
            case "Small":
                pizza.setPrice(8.99);
                break;
            case "Medium":
                pizza.setPrice(10.99);
                break;
            case "Large":
                pizza.setPrice(12.99);
                break;
            default:
                pizza.setPrice(10.99); 
                break;
        }

        pizzaService.savePizza(pizza);  // Save pizza to db

        // Retrieve the cart from the session
        List<Pizza> cartPizzas = getCartPizzas(session);
        cartPizzas.add(pizza);
        session.setAttribute("cartPizzas", cartPizzas);

        redirectAttributes.addFlashAttribute("success", "Pizza created and added to cart!");
        return "redirect:/pizzas/cart";
    }

    // Display the cart with the pizzas
    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        List<Pizza> cartPizzas = getCartPizzas(session);
        model.addAttribute("cartPizzas", cartPizzas);
        return "cart";
    }

    @GetMapping("/edit/{pizzaId}")
    public String editPizzaForm(@PathVariable("pizzaId") Long pizzaId, Model model) {
        Optional<Pizza> pizza = pizzaService.findPizzaById(pizzaId);
        if (pizza.isEmpty()) {
            return "redirect:/pizzas/cart"; 
        }
        model.addAttribute("pizza", pizza.get());
        model.addAttribute("availableToppings", pizzaService.getAvailableToppings());
        return "editPizza"; 
    }

    // Update an existing pizza
    @PostMapping("/update/{pizzaId}")
    public String updatePizza(@PathVariable("pizzaId") Long pizzaId, @Valid @ModelAttribute("pizza") Pizza pizza, BindingResult result, RedirectAttributes redirectAttributes, HttpSession session) {
        if (result.hasErrors()) {
            return "editPizza";  // back to form if validation fails
        }

    
        Pizza existingPizza = pizzaService.findPizzaById(pizzaId).orElse(null);
        if (existingPizza == null) {
            return "redirect:/pizzas/cart";  // if there's no pizza 
        }

        existingPizza.setSize(pizza.getSize());
        existingPizza.setCrust(pizza.getCrust());
        existingPizza.setToppings(pizza.getToppings());

        switch (pizza.getSize()) {
            case "Small":
                existingPizza.setPrice(8.99);
                break;
            case "Medium":
                existingPizza.setPrice(10.99);
                break;
            case "Large":
                existingPizza.setPrice(12.99);
                break;
            default:
                existingPizza.setPrice(10.99); 
                break;
        }

        // Save the new pizza
        pizzaService.savePizza(existingPizza);

        // Update the cart
        List<Pizza> cartPizzas = getCartPizzas(session);
        for (int i = 0; i < cartPizzas.size(); i++) {
            if (cartPizzas.get(i).getId().equals(pizzaId)) {
                cartPizzas.set(i, existingPizza); 
                break;
            }
        }
        session.setAttribute("cartPizzas", cartPizzas);

        redirectAttributes.addFlashAttribute("success", "Pizza updated successfully!");
        return "redirect:/pizzas/cart";  // back to cart after update
    }

    @PostMapping("/delete/{pizzaId}")
    public String deletePizza(@PathVariable("pizzaId") Long pizzaId, HttpSession session, RedirectAttributes redirectAttributes) {
        System.out.println("Attempting to delete pizza with ID: " + pizzaId); // Logging the ID
        
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to delete a pizza.");
            return "redirect:/"; 
        }
        
        pizzaService.deletePizza(pizzaId);
        
        // Update the cart
        List<Pizza> cartPizzas = getCartPizzas(session);
        cartPizzas.removeIf(pizza -> pizza.getId().equals(pizzaId)); // Remove deleted pizza
        session.setAttribute("cartPizzas", cartPizzas);

        redirectAttributes.addFlashAttribute("success", "Pizza deleted successfully!");
        return "redirect:/pizzas/cart";  
    }

    // using this to get the cart from session
    @SuppressWarnings("unchecked")
    private List<Pizza> getCartPizzas(HttpSession session) {
        List<Pizza> cartPizzas = (List<Pizza>) session.getAttribute("cartPizzas");
        if (cartPizzas == null) {
            cartPizzas = new ArrayList<>();
        }
        return cartPizzas;
    }
}
