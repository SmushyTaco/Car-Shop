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
        inHousePart.id = id;
        assertEquals(id, inHousePart.id);
        outsourcedPart.id = id;
        assertEquals(id, outsourcedPart.id);
    }
    @Test
    void name() {
        String name = "test in house part";
        inHousePart.name = name;
        assertEquals(name, inHousePart.name);
        name = "test outsourced part";
        outsourcedPart.name = name;
        assertEquals(name, outsourcedPart.name);
    }
    @Test
    void price() {
        final double price = 1.0;
        inHousePart.price = price;
        assertEquals(price, inHousePart.price);
        outsourcedPart.price = price;
        assertEquals(price, outsourcedPart.price);
    }
    @Test
    void minInv() {
        final int minInv = 3;
        inHousePart.minInv = minInv;
        assertEquals(minInv, inHousePart.minInv);
        outsourcedPart.minInv = minInv;
        assertEquals(minInv, outsourcedPart.minInv);
    }
    @Test
    void maxInv() {
        final int maxInv = 8;
        inHousePart.maxInv = maxInv;
        assertEquals(maxInv, inHousePart.maxInv);
        outsourcedPart.maxInv = maxInv;
        assertEquals(maxInv, outsourcedPart.maxInv);
    }
    @Test
    void inv() {
        final int inv = 5;
        inHousePart.inv = inv;
        assertEquals(inv, inHousePart.inv);
        outsourcedPart.inv = inv;
        assertEquals(inv, outsourcedPart.inv);
    }
    @Test
    void products() {
        final Product productOne = new Product();
        final Product productTwo = new Product();
        final Set<Product> products = new HashSet<>();
        products.add(productOne);
        products.add(productTwo);
        inHousePart.products = products;
        assertEquals(products, inHousePart.products);
        outsourcedPart.products = products;
        assertEquals(products, outsourcedPart.products);
    }
    @Test
    void testToString() {
        String name = "test in house part";
        inHousePart.name = name;
        assertEquals(name, inHousePart.toString());
        name = "test outsourced part";
        outsourcedPart.name = name;
        assertEquals(name, outsourcedPart.toString());
    }
    @Test
    void testEquals() {
        final long id = 1L;
        inHousePart.id = id;
        final Part inHousePartTwo = new InHousePart();
        inHousePartTwo.id = id;
        assertEquals(inHousePart, inHousePartTwo);
        outsourcedPart.id = id;
        final Part outsourcedPartTwo = new OutsourcedPart();
        outsourcedPartTwo.id = id;
        assertEquals(outsourcedPart, outsourcedPartTwo);
    }
    @Test
    void testHashCode() {
        final long id = 1L;
        inHousePart.id = id;
        outsourcedPart.id = id;
        assertEquals(inHousePart.hashCode(), outsourcedPart.hashCode());
    }
}