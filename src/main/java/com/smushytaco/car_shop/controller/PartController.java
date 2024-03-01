package com.smushytaco.car_shop.controller;
import com.smushytaco.car_shop.service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
public class PartController {
    private final PartService partService;
    public PartController(final PartService partService) { this.partService = partService; }
    @DeleteMapping("/delete-part")
    public String deletePart(@RequestParam("part-id") final long id) {
        partService.deleteById(id);
        return "redirect:main-screen";
    }
}