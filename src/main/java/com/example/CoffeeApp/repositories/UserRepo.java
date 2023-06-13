package com.example.CoffeeApp.repositories;

import com.example.CoffeeApp.domains.User;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepo extends CrudRepository<User, Serializable> {

    boolean existsByEmailId(String emailId);

    User findByUserId(Long userId);

}
