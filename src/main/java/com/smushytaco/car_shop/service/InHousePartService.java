package com.smushytaco.car_shop.service;
import com.smushytaco.car_shop.domain.InHousePart;
import com.smushytaco.car_shop.domain.Product;
import java.util.List;
import java.util.Set;
public interface InHousePartService {
    List<InHousePart> findAll();
    InHousePart findById(final long id);
    InHousePart save (final InHousePart inHousePart);
    List<InHousePart> listAll(final String keyword);
    Set<Product> getProducts(final long id);
}