package com.smushytaco.car_shop.service;

import com.smushytaco.car_shop.domain.OutsourcedPart;
import com.smushytaco.car_shop.domain.Product;

import java.util.List;
import java.util.Set;

public interface OutsourcedPartService {
        List<OutsourcedPart> findAll();
        OutsourcedPart findById(final long id);
        OutsourcedPart save (final OutsourcedPart outsourcedPart);
        List<OutsourcedPart> listAll(final String keyword);
        Set<Product> getProducts(final long id);
}