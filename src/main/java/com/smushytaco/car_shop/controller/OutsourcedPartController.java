package com.smushytaco.car_shop.controller;

import com.smushytaco.car_shop.domain.OutsourcedPart;
import com.smushytaco.car_shop.service.OutsourcedPartService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public final class OutsourcedPartController {
    private static final String OUTSOURCED_PART_FORM = "outsourced-part-form";
    private static final String OUTSOURCED_PART = "outsourcedPart";
    private final OutsourcedPartService outsourcedPartService;
    public OutsourcedPartController(final OutsourcedPartService outsourcedPartService) { this.outsourcedPartService = outsourcedPartService; }
    @GetMapping("/show-outsourced-part-form-for-update")
    public String showPartFormForUpdate(@RequestParam("part-id") final long id, final Model model) {
        model.addAttribute(OUTSOURCED_PART, outsourcedPartService.findById(id));
        return OUTSOURCED_PART_FORM;
    }
    @GetMapping("/show-form-add-out-part")
    public String showFormAddOutsourcedPart(final Model model) {
        model.addAttribute(OUTSOURCED_PART, new OutsourcedPart());
        return OUTSOURCED_PART_FORM;
    }
    @RequestMapping(value = "/show-form-add-out-part", method = { RequestMethod.POST, RequestMethod.PATCH })
    public String submitForm(@Valid final OutsourcedPart outsourcedPart, final BindingResult bindingResult, final Model model) {
        model.addAttribute(OUTSOURCED_PART, outsourcedPart);
        if (bindingResult.hasErrors()) return OUTSOURCED_PART_FORM;
        outsourcedPart.products = outsourcedPartService.getProducts(outsourcedPart.id);
        outsourcedPartService.save(outsourcedPart);
        return "redirect:main-screen";
    }
}