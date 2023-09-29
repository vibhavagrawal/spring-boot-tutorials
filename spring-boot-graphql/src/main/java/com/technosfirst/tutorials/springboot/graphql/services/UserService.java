package com.technosfirst.tutorials.springboot.graphql.services;

import com.technosfirst.tutorials.springboot.graphql.exceptions.UserAlreadyExistsException;
import com.technosfirst.tutorials.springboot.graphql.exceptions.UserNotFoundException;
import com.technosfirst.tutorials.springboot.graphql.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    List<User> users = new ArrayList<>();

    public User createUser(String username, String password) {
        if (userExists(username))
            throw new UserAlreadyExistsException("A user already exists with this username, please try another one");
        User user = new User();
        user.setId(users.size() + 1);
        user.setPassword(password);
        user.setUsername(username);
        users.add(user);
        return user;
    }

    public User getUser(String username, String password) {
        for (User user : users)
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        throw new UserNotFoundException("We were unable to find a user with the provided credentials", "username");
    }

    private boolean userExists(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return true;
        return false;
    }
}

