package com.bowemary.pizzapetes.services;

import com.bowemary.pizzapetes.models.User;
import com.bowemary.pizzapetes.models.LoggedInUser;
import com.bowemary.pizzapetes.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    // Check if a user exists by ID
    public boolean existsById(Long id) {
        return userRepo.existsById(id);
    }

    // Register a new user
    public User register(User newUser, BindingResult result) {
        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
        if (potentialUser.isPresent()) {
            result.rejectValue("email", "Matches", "An account with that email already exists.");
        }
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

    // Login a user
    public User login(LoggedInUser newLogin, BindingResult result) {
        Optional<User> potentialUser = userRepo.findByEmail(newLogin.getEmail());
        if (!potentialUser.isPresent()) {
            result.rejectValue("email", "Matches", "Email not found.");
            return null;
        }
        User user = potentialUser.get();
        if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "Matches", "Invalid password.");
        }
        if (result.hasErrors()) {
            return null;
        }
        return user;
    }

    // get a user by ID
    public Optional<User> findUserById(Long id) {
        return userRepo.findById(id);
    }

    // Get the logged-in user by ID
    public User getLoggedInUser(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }

    // Update user
    public User updateUser(User user) {
        return userRepo.save(user); 
    }
}
