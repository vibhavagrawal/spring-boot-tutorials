package com.technosfirst.tutorials.springboot.graphql.controllers;

import com.technosfirst.tutorials.springboot.graphql.models.Book;
import com.technosfirst.tutorials.springboot.graphql.models.User;
import com.technosfirst.tutorials.springboot.graphql.services.BookService;
import com.technosfirst.tutorials.springboot.graphql.services.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    public User createUser(String username, String password) {
        return userService.createUser(username, password);
    }

    public Book createBook(CreateBookInput bookParameters) {
        return bookService.createBook(bookParameters);
    }
}
