package com.smushytaco.car_shop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class ProductTest {
    Product product;
    @BeforeEach
    void setUp() { product = new Product(); }
    @Test
    void id() {
        final long id = 4L;
        product.setId(id);
        assertEquals(id, product.getId());
    }
    @Test
    void name() {
        final String name = "test product";
        product.setName(name);
        assertEquals(name, product.getName());
    }
    @Test
    void price() {
        final double price = 1.0;
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }
    @Test
    void inv() {
        final int inv = 5;
        product.setInv(inv);
        assertEquals(inv, product.getInv());
    }
    @Test
    void parts() {
        final Part outsourcedPart = new OutsourcedPart();
        final Part inHousePart = new InHousePart();
        final Set<Part> parts = new HashSet<>();
        parts.add(outsourcedPart);
        parts.add(inHousePart);
        product.setParts(parts);
        assertEquals(parts, product.getParts());
    }
    @Test
    void testToString() {
        final String name = "test product";
        product.setName(name);
        assertEquals(name, product.toString());
    }
    @Test
    void testEquals() {
        product.setId(1L);
        final Product productTwo = new Product();
        productTwo.setId(1L);
        assertEquals(product, productTwo);
    }
    @Test
    void testHashCode() {
        product.setId(1L);
        final Product productTwo = new Product();
        productTwo.setId(1L);
        assertEquals(product.hashCode(), productTwo.hashCode());
    }
}