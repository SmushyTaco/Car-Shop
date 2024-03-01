package com.smushytaco.car_shop.controller;
import com.smushytaco.car_shop.domain.InHousePart;
import com.smushytaco.car_shop.service.InHousePartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Controller
public class InHousePartController {
    private static final String IN_HOUSE_PART_FORM = "in-house-part-form";
    private static final String IN_HOUSE_PART = "inHousePart";
    private final InHousePartService inHousePartService;
    public InHousePartController(InHousePartService inHousePartService) { this.inHousePartService = inHousePartService; }
    @GetMapping("/show-in-house-part-form-for-update")
    public String showPartFormForUpdate(@RequestParam("part-id") final long id, final Model model) {
        model.addAttribute(IN_HOUSE_PART, inHousePartService.findById(id));
        return IN_HOUSE_PART_FORM;
    }
    @GetMapping("/show-form-add-in-part")
    public String showFormAddInHousePart(final Model model) {
        model.addAttribute(IN_HOUSE_PART, new InHousePart());
        return IN_HOUSE_PART_FORM;
    }
    @RequestMapping(value = "/show-form-add-in-part", method = { RequestMethod.POST, RequestMethod.PATCH })
    public String submitForm(@Valid final InHousePart inHousePart, final BindingResult bindingResult, final Model model) {
        model.addAttribute(IN_HOUSE_PART, inHousePart);
        if (bindingResult.hasErrors()) return IN_HOUSE_PART_FORM;
        inHousePart.setProducts(inHousePartService.getProducts(inHousePart.getId()));
        inHousePartService.save(inHousePart);
        return "redirect:main-screen";
    }
}