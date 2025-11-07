package com.smushytaco.car_shop.controller;

import com.smushytaco.car_shop.domain.InHousePart;
import com.smushytaco.car_shop.domain.OutsourcedPart;
import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.service.InHousePartService;
import com.smushytaco.car_shop.service.OutsourcedPartService;
import com.smushytaco.car_shop.service.PartService;
import com.smushytaco.car_shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public final class MainScreenController {
    private final OutsourcedPartService outsourcedPartService;
    private final InHousePartService inHousePartService;
    private final PartService partService;
    private final ProductService productService;
    public MainScreenController(final OutsourcedPartService outsourcedPartService, final InHousePartService inHousePartService, final PartService partService, final ProductService productService) {
        this.outsourcedPartService = outsourcedPartService;
        this.inHousePartService = inHousePartService;
        this.partService = partService;
        this.productService = productService;
    }
    @GetMapping("/main-screen")
    public String listPartsAndProducts(final Model model, @RequestParam(value = "outsourced-part-keyword", required = false) final String outsourcedPartKeyword, @RequestParam(value = "in-house-part-keyword", required = false) final String inHousePartKeyword, @RequestParam(value = "product-keyword", required = false) final String productKeyword) {
        final List<OutsourcedPart> outsourcedPartList = outsourcedPartService.listAll(outsourcedPartKeyword);
        model.addAttribute("outsourcedParts", outsourcedPartList);
        final List<InHousePart> inHousePartList = inHousePartService.listAll(inHousePartKeyword);
        model.addAttribute("inHouseParts", inHousePartList);
        final List<Product> productList = productService.listAll(productKeyword);
        model.addAttribute("products", productList);
        final List<Part> parts = partService.findAll();
        final Map<Long, Boolean> unassociatedParts = new HashMap<>();
        for (final Part part : parts) unassociatedParts.put(part.getId(), partService.getProducts(part.getId()).isEmpty());
        model.addAttribute("unassociatedParts", unassociatedParts);
        return "main-screen";
    }
}