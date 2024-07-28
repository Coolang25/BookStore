package com.trinity.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/books"})
    public String book() {
        return "book";
    }

    @GetMapping("/authors")
    public String author() {
        return "author";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/unreturned_books")
    public String unReturnedBook() {
        return "unreturned_books";
    }
}
