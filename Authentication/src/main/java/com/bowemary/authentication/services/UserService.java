package com.bowemary.authentication.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.bowemary.authentication.models.LoggedInUser;
import com.bowemary.authentication.models.User;
import com.bowemary.authentication.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	// TO-DO: Write register and login methods!
	public User register(User newUser, BindingResult result) {
		// see if the user already has an account
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if (potentialUser.isPresent())
			result.rejectValue("email", "Matches", "An account with that email already exists");
		// if we get this far we have unique email
		// need to check if passwords match
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Passwords do not match.");
		}
		if (result.hasErrors()) {

			return null;
		}
		String hashedPassword = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashedPassword);
		return userRepo.save(newUser);
	}

	public User login(LoggedInUser newLoginObject, BindingResult result) {
		// check if email exists in DB
		// if it does, check their pw against whats stored in DB
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email not found, try registering.");
			return null;
		}
		User user = potentialUser.get();
		if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid login attempt, try again.");
		}
		if (result.hasErrors()) {
			return null;
		} else {
			return user;
		}

	}

	public User getLoggedInUser(Long id) {
		Optional<User> potentialUser = userRepo.findById(id);
		if (potentialUser.isPresent()) {
			return potentialUser.get();
		}
		return null;
	}

}
