package com.technosfirst.tutorials.springboot.graphql.controllers;

import com.technosfirst.tutorials.springboot.graphql.models.Book;
import com.technosfirst.tutorials.springboot.graphql.models.User;
import com.technosfirst.tutorials.springboot.graphql.services.BookService;
import com.technosfirst.tutorials.springboot.graphql.services.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    public User getUser(String username, String password) {
        return userService.getUser(username, password);
    }

    public Book getBook(String title) {
        return bookService.getBook(title);
    }
}
