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
        product.id = id;
        assertEquals(id, product.id);
    }
    @Test
    void name() {
        final String name = "test product";
        product.name = name;
        assertEquals(name, product.name);
    }
    @Test
    void price() {
        final double price = 1.0;
        product.price = price;
        assertEquals(price, product.price);
    }
    @Test
    void inv() {
        final int inv = 5;
        product.inv = inv;
        assertEquals(inv, product.inv);
    }
    @Test
    void parts() {
        final Part outsourcedPart = new OutsourcedPart();
        final Part inHousePart = new InHousePart();
        final Set<Part> parts = new HashSet<>();
        parts.add(outsourcedPart);
        parts.add(inHousePart);
        product.parts = parts;
        assertEquals(parts, product.parts);
    }
    @Test
    void testToString() {
        final String name = "test product";
        product.name = name;
        assertEquals(name, product.toString());
    }
    @Test
    void testEquals() {
        final long id = 1L;
        product.id = id;
        final Product productTwo = new Product();
        productTwo.id = id;
        assertEquals(product, productTwo);
    }
    @Test
    void testHashCode() {
        final long id = 1L;
        product.id = id;
        final Product productTwo = new Product();
        productTwo.id = id;
        assertEquals(product.hashCode(), productTwo.hashCode());
    }
}