package com.smushytaco.car_shop.service;
import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.domain.Product;

import java.util.List;
import java.util.Set;
public interface ProductService {
    List<Product> findAll();
    Product findById(final long id);
    Product save (final Product product);
    void deleteById(final long id);
    List<Product> listAll(final String keyword);
    Set<Part> getParts(final long id);
    void associatePartToProduct(final long productId, final long partId);
    void removePartFromProduct(final long productId, final long partId);
    boolean productPriceIsValid(final Product product, final Part extraPart);
}