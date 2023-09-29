package com.technosfirst.tutorials.springboot.graphql.services;

import com.technosfirst.tutorials.springboot.graphql.controllers.CreateBookInput;
import com.technosfirst.tutorials.springboot.graphql.exceptions.UserAlreadyExistsException;
import com.technosfirst.tutorials.springboot.graphql.exceptions.UserNotFoundException;
import com.technosfirst.tutorials.springboot.graphql.models.Book;
import com.technosfirst.tutorials.springboot.graphql.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {
    List<Book> books = new ArrayList<>();

    public Book createBook(CreateBookInput bookInput) {
        if (bookExists(bookInput.getTitle()))
            throw new UserAlreadyExistsException("A book already exists with this title, please try another one");
        Book book = new Book();
        book.setId(books.size() + 1);
        book.setTitle(bookInput.getTitle());
        book.setAuthor(bookInput.getAuthor());
        book.setDescription(bookInput.getDescription());
        books.add(book);
        return book;
    }

    public Book getBook(String title) {
        for (Book book : books)
            if (book.getTitle().equals(title))
                return book;
        throw new UserNotFoundException("We were unable to find a book with the provided title", "title");
    }

    private boolean bookExists(String title) {
        for (Book book : books)
            if (book.getTitle().equals(title))
                return true;
        return false;
    }
}

