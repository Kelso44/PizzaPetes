package com.bowemary.pizzapetes.controllers;

import com.bowemary.pizzapetes.models.Order;
import com.bowemary.pizzapetes.models.OrderWithPizzas;
import com.bowemary.pizzapetes.models.Pizza;
import com.bowemary.pizzapetes.models.User;
import com.bowemary.pizzapetes.services.OrderService;
import com.bowemary.pizzapetes.services.PizzaService;
import com.bowemary.pizzapetes.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private PizzaService pizzaService;

    @PostMapping
    public String createOrder(HttpSession session, @RequestParam List<Long> pizzaIds, RedirectAttributes redirectAttributes) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null || pizzaIds == null || pizzaIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to place an order.");
            return "redirect:/";
        }

        User loggedInUser = userService.getLoggedInUser(userId);
        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return "redirect:/";
        }

        Order order = new Order();
        order.setUserId(userId);
        order.setPizzaIds(pizzaIds);

        try {
            orderService.saveOrder(order);
            redirectAttributes.addFlashAttribute("success", "Order placed successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred while placing the order.");
            return "redirect:/";
        }

        return "redirect:/orders/list";
    }

    @GetMapping("/list")
    public String getOrderList(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }

        List<OrderWithPizzas> ordersWithPizzas = orderService.getOrdersWithPizzasByUserId(userId);
        model.addAttribute("ordersWithPizzas", ordersWithPizzas);
        return "orderList";
    }

    @GetMapping("/edit/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        Order order = orderService.findOrderById(id);
        model.addAttribute("order", order);

        List<Pizza> orderPizzas = new ArrayList<>();
        for (Long pizzaId : order.getPizzaIds()) {
            pizzaService.findPizzaById(pizzaId).ifPresent(orderPizzas::add);
        }
        model.addAttribute("orderPizzas", orderPizzas);

        List<String> availableToppings = pizzaService.getAvailableToppings();
        model.addAttribute("availableToppings", availableToppings);

        return "editOrder";
    }

    @PostMapping("/update")
    public String updateOrder(@ModelAttribute Order order, HttpSession session, @RequestParam List<Long> pizzaIds) {
        order.setPizzaIds(pizzaIds);
        orderService.saveOrder(order);
        return "redirect:/orders/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            orderService.deleteOrder(id);
            redirectAttributes.addFlashAttribute("success", "Order deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error occurred while deleting the order.");
        }
        return "redirect:/orders/list";
    }

    @GetMapping("/reorder/{id}")
    public String reorder(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to reorder.");
            return "redirect:/";
        }

        Order existingOrder = orderService.findOrderById(id);
        if (existingOrder != null) {
            Order newOrder = new Order();
            newOrder.setUserId(userId);
            newOrder.setPizzaIds(new ArrayList<>(existingOrder.getPizzaIds()));
            orderService.saveOrder(newOrder);
            redirectAttributes.addFlashAttribute("success", "Order reordered successfully!");
            return "redirect:/orders/list";
        }

        redirectAttributes.addFlashAttribute("error", "Error occurred while reordering.");
        return "redirect:/error";
    }
}
