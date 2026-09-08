package com.kharchenko.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class RootController {
    @GetMapping("/")
    public String routeByRole(){
        return "route-by-role";
    }
}
