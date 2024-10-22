package com.bowemary.pizzapetes.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bowemary.pizzapetes.models.LoggedInUser;
import com.bowemary.pizzapetes.models.User;
import com.bowemary.pizzapetes.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoggedInUser());
        return "index";
    }

    @PostMapping("/register/user")
    public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, 
                               Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        userService.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoggedInUser());
            return "index";
        }
        session.setAttribute("userId", newUser.getId());
        model.addAttribute("user", newUser);
        redirectAttributes.addFlashAttribute("success", "Registration successful!");
        return "redirect:/homepage";
    }

    @GetMapping("/homepage")
    public String showHomepage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Long userId = (Long) session.getAttribute("userId");
        
        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to access this page.");
            return "redirect:/";
        }
        
        User user = userService.findUserById(userId).orElse(null);
        model.addAttribute("user", user);
        return "homepage";
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }
        User user = userService.findUserById(userId).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }
    
    @GetMapping("/profile/edit/{userId}")
    public String editProfile(@PathVariable("userId") Long userId, HttpSession session, Model model) {
        Long sessionUserId = (Long) session.getAttribute("userId");
        if (sessionUserId == null || !sessionUserId.equals(userId)) {
            return "redirect:/";
        }

        User user = userService.findUserById(userId).orElse(null);
        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        model.addAttribute("statesList", Arrays.asList("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", 
                "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", 
                "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", 
                "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"));

        return "editProfile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "editProfile";
        }

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null || !sessionUserId.equals(user.getId())) {
            redirectAttributes.addFlashAttribute("error", "Unauthorized access.");
            return "redirect:/";
        }

        User existingUser = userService.findUserById(user.getId()).orElse(null);
        if (existingUser == null) {
            return "redirect:/";
        }

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setState(user.getState());

        userService.updateUser(existingUser);
        redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        return "redirect:/profile";
    }

    @PostMapping("/login/user")
    public String loginUser(@Valid @ModelAttribute("newLogin") LoggedInUser newLogin, BindingResult result, 
                            Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.login(newLogin, result);
        if (result.hasErrors() || user == null) {
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("userId", user.getId());
        redirectAttributes.addFlashAttribute("success", "Login successful!");
        return "redirect:/homepage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have been logged out successfully.");
        return "redirect:/";
    }
}
