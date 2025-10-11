package com.smushytaco.car_shop.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RootController {
    @GetMapping("/")
    public String about() { return "redirect:/main-screen"; }
}