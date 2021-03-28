package ru.saray.jm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello! My name is Rimma");
        messages.add("It's my first Spring MVC application");
        messages.add("Click on the link below to see my users table and try to CRUD it ;)");
        model.addAttribute("messages", messages);
        return "index";
    }

}
