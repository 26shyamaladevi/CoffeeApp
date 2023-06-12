package com.example.CoffeeApp.services;

import com.example.CoffeeApp.repositories.UserRepo;
import com.example.CoffeeApp.domains.User;
import java.util.List;
import org.springframework.stereotype.Service;

/* Service class responsible for user-related operations.
Implements business logic for user management and interacts with UserRepo for data access */

@Service
public class UserService {
    public final UserRepo userrepo;
    public final RoleService roleService;

    // Constructor injection of UserRepo dependency
    public UserService(UserRepo userRepo, RoleService roleservice) {
        this.userrepo = userRepo;
        this.roleService = roleservice;
    }

    // Retrieve all users
    public List<User> viewUsers() {
        return (List<User>) userrepo.findAll();
    }

    // Add a new user
    public void addUsers(User user) {
        if (user.getRole() != null) {
            roleService.addRoles(user.getRole());
            userrepo.save(user);

        }

    }

    // Check if a user with the given emailId already exists
    public boolean isexistsByEmail(String emailId) {
        return !userrepo.existsByEmailId(emailId);

    }

    // Check if a user with the given userId exists
    public boolean isexistsByUserId(long id) {
        return userrepo.existsById(id);
    }

    // Update an existing user
    public String updateUser(User u) {

        userrepo.save(u);
        return "User " + u.getLastName() + " " + "with id:" + " " + u.getUserId() + " " + "is updated sucessfully.";
    }

    // Delete a user by ID
    public boolean deleteUser(Long id) {
        if (userrepo.existsById(id)) {
            userrepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
