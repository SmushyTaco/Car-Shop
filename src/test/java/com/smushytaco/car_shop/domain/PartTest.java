package com.smushytaco.car_shop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class PartTest {
    Part inHousePart;
    Part outsourcedPart;
    @BeforeEach
    void setUp() {
        inHousePart = new InHousePart();
        outsourcedPart = new OutsourcedPart();
    }
    @Test
    void id() {
        final long id = 4L;
        inHousePart.setId(id);
        assertEquals(id, inHousePart.getId());
        outsourcedPart.setId(id);
        assertEquals(id, outsourcedPart.getId());
    }
    @Test
    void name() {
        String name = "test in house part";
        inHousePart.setName(name);
        assertEquals(name, inHousePart.getName());
        name = "test outsourced part";
        outsourcedPart.setName(name);
        assertEquals(name, outsourcedPart.getName());
    }
    @Test
    void price() {
        final double price = 1.0;
        inHousePart.setPrice(price);
        assertEquals(price, inHousePart.getPrice());
        outsourcedPart.setPrice(price);
        assertEquals(price, outsourcedPart.getPrice());
    }
    @Test
    void minInv() {
        final int minInv = 3;
        inHousePart.setMinInv(minInv);
        assertEquals(minInv, inHousePart.getMinInv());
        outsourcedPart.setMinInv(minInv);
        assertEquals(minInv, outsourcedPart.getMinInv());
    }
    @Test
    void maxInv() {
        final int maxInv = 8;
        inHousePart.setMaxInv(maxInv);
        assertEquals(maxInv, inHousePart.getMaxInv());
        outsourcedPart.setMaxInv(maxInv);
        assertEquals(maxInv, outsourcedPart.getMaxInv());
    }
    @Test
    void inv() {
        final int inv = 5;
        inHousePart.setInv(inv);
        assertEquals(inv, inHousePart.getInv());
        outsourcedPart.setInv(inv);
        assertEquals(inv, outsourcedPart.getInv());
    }
    @Test
    void products() {
        final Product productOne = new Product();
        final Product productTwo = new Product();
        final Set<Product> products = new HashSet<>();
        products.add(productOne);
        products.add(productTwo);
        inHousePart.setProducts(products);
        assertEquals(products, inHousePart.getProducts());
        outsourcedPart.setProducts(products);
        assertEquals(products, outsourcedPart.getProducts());
    }
    @Test
    void testToString() {
        String name = "test in house part";
        inHousePart.setName(name);
        assertEquals(name, inHousePart.toString());
        name = "test outsourced part";
        outsourcedPart.setName(name);
        assertEquals(name, outsourcedPart.toString());
    }
    @Test
    void testEquals() {
        inHousePart.setId(1L);
        final Part inHousePartTwo = new InHousePart();
        inHousePartTwo.setId(1L);
        assertEquals(inHousePart, inHousePartTwo);
        outsourcedPart.setId(1L);
        final Part outsourcedPartTwo = new OutsourcedPart();
        outsourcedPartTwo.setId(1L);
        assertEquals(outsourcedPart, outsourcedPartTwo);
    }
    @Test
    void testHashCode() {
        inHousePart.setId(1L);
        outsourcedPart.setId(1L);
        assertEquals(inHousePart.hashCode(), outsourcedPart.hashCode());
    }
}