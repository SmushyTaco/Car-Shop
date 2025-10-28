package com.smushytaco.car_shop.service;

import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.domain.Product;

import java.util.List;
import java.util.Set;

public interface PartService {
    List<Part> findAll();
    Part findById(final long id);
    Part save (final Part part);
    void deleteById(final long id);
    Set<Product> getProducts(final long id);
}