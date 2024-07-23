package com.trinity.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String book() {
        return "book.html";
    }

    @GetMapping("/authorList")
    public String author() {
        return "author.html";
    }
}
