package com.smushytaco.car_shop.controller;

import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.service.PartService;
import com.smushytaco.car_shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
public final class ProductController {
    private final PartService partService;
    private final ProductService productService;
    private static final String REDIRECT_MAIN_SCREEN = "redirect:main-screen";
    public ProductController(final PartService partService, final ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }
    private String prepareProductForm(final Model model, final Product product, final boolean addAttribute, final boolean isUpdate) {
        if (addAttribute) model.addAttribute("product", product);
        if (!isUpdate) return "new-product-form";
        final List<Part> availableParts = new ArrayList<>();
        final Set<Part> associatedParts = productService.getParts(product.getId());
        for (final Part part : partService.findAll()) if (!associatedParts.contains(part)) availableParts.add(part);
        final Map<Long, Boolean> partPriceValidity = new HashMap<>();
        for (final Part part : availableParts) partPriceValidity.put(part.getId(), productService.productPriceIsValid(product, part));
        model.addAttribute("partPriceValidity", partPriceValidity);
        model.addAttribute("availableParts", availableParts);
        model.addAttribute("associatedParts", associatedParts);
        return "update-product-form";
    }
    @GetMapping("/add-product")
    public String showFormAddPart(final Model model) { return prepareProductForm(model, new Product(), true, false); }
    @PostMapping("/add-product")
    public String submitFormForNewProduct(@Valid final Product product, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) return prepareProductForm(model, product, true, false);
        product.setInv(0);
        productService.save(product);
        return REDIRECT_MAIN_SCREEN;
    }
    @GetMapping("/update-product")
    public String showProductFormForUpdate(@RequestParam("product-id") final long id, final Model model) {
        model.addAttribute("parts", partService.findAll());
        return prepareProductForm(model, productService.findById(id), true, true);
    }
    @PatchMapping("/update-product")
    public String submitFormForUpdatingProduct(@Valid final Product product, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) return prepareProductForm(model, product, false, true);
        final Product existingProduct = productService.findById(product.getId());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        if (product.getInv() > existingProduct.getInv()) {
            final Set<Part> parts = productService.getParts(existingProduct.getId());
            for (final Part part : parts) {
                part.setInv(part.getInv() - (product.getInv() - existingProduct.getInv()));
                partService.save(part);
            }
        }
        existingProduct.setInv(product.getInv());
        productService.save(existingProduct);
        return REDIRECT_MAIN_SCREEN;
    }
    @DeleteMapping("/delete-product")
    public String deleteProduct(@RequestParam("product-id") final long id) {
        productService.getParts(id).stream().map(Part::getId).toList().forEach(partId -> productService.removePartFromProduct(id, partId));
        productService.deleteById(id);
        return REDIRECT_MAIN_SCREEN;
    }
    @PostMapping("/associate-part")
    public String associatePart(@RequestParam("part-id") final long partId, @RequestParam("product-id") final long productId, final Model model) {
        productService.associatePartToProduct(productId, partId);
        return prepareProductForm(model, productService.findById(productId), true, true);
    }
    @DeleteMapping("/remove-part")
    public String removePart(@RequestParam("part-id") final long partId, @RequestParam("product-id") final long productId, final Model model) {
        productService.removePartFromProduct(productId, partId);
        return prepareProductForm(model, productService.findById(productId), true, true);
    }
    @PatchMapping("/buy-product")
    public String buyProduct(@RequestParam("product-id") final long id) {
        final Product product = productService.findById(id);
        product.setInv(product.getInv() - 1);
        productService.save(product);
        return REDIRECT_MAIN_SCREEN;
    }
}