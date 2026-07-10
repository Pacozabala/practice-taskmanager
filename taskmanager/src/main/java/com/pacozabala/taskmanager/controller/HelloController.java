package com.pacozabala.taskmanager.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import com.pacozabala.taskmanager.model.Message;

@RestController
public class HelloController {
    
    @GetMapping("/hello")
    public Message hello() {
        return new Message("Hello", "Paco");
    }
    

}
