package com.example.finalLabSensores.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController


public class PingController {
    @GetMapping("/Pong")
    public String Ping(){
        return "Pong";
    }
}
