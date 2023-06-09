package com.example.CoffeeApp.repositories;

import com.example.CoffeeApp.domains.User;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Serializable> {

    boolean existsByEmailId(String emailId);

}
