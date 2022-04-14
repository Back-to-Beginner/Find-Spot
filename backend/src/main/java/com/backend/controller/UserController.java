package com.backend.controller;

import com.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // This means that this class is a Controller
@RequestMapping(path = "/users") // This means URL's start with /demo (after Application path)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search-all")
    public void search() {

    }


}
