package com.smushytaco.car_shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public final class AboutController {
    @GetMapping("/about")
    public String about() { return "about"; }
}