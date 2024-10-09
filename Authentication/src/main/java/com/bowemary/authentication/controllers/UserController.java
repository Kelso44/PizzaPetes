package com.bowemary.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bowemary.authentication.models.LoggedInUser;
import com.bowemary.authentication.models.User;
import com.bowemary.authentication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	UserService users;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoggedInUser());
		return "index.jsp";
	}

	@PostMapping("/register/user")
	public String registerUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model,
			HttpSession session) {
		users.register(newUser, result);
		if (result.hasErrors()) {
			
			model.addAttribute("newLogin", new LoggedInUser());
			return "index.jsp";
		}
		// Log the user in (store their ID in session, etc.)
		session.setAttribute("userId", newUser.getId());
		return "redirect:/home";
	}

	@PostMapping("/login/user")
	public String loginUser(@Valid @ModelAttribute("newLogin") LoggedInUser newLogin, BindingResult result, Model model,
			HttpSession session) {
		User user = users.login(newLogin, result);
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {
			session.setAttribute("userId", user.getId());
			return "redirect:/home";
			// Log the user in (store their ID in session, etc.)
		}

	}

//homepage

	@GetMapping("/home")
	public String homepage(HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		} else {
			model.addAttribute("user", users.getLoggedInUser(userId));
			return "home.jsp";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";

	}
}