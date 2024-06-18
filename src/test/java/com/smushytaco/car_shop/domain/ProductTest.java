package com.smushytaco.car_shop.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    Product product;
    @BeforeEach
    public void setUp() { product = new Product(); }
    @Test
    void id() {
        long id = 4L;
        product.setId(id);
        assertEquals(product.getId(), id);
    }
    @Test
    void name() {
        String name = "test product";
        product.setName(name);
        assertEquals(name, product.getName());
    }
    @Test
    void price() {
        double price = 1.0;
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }
    @Test
    void inv() {
        int inv = 5;
        product.setInv(inv);
        assertEquals(inv, product.getInv());
    }
    @Test
    void parts() {
        Part outsourcedPart = new OutsourcedPart();
        Part inHousePart = new InHousePart();
        Set<Part> parts = new HashSet<>();
        parts.add(outsourcedPart);
        parts.add(inHousePart);
        product.setParts(parts);
        assertEquals(parts, product.getParts());
    }
    @Test
    void testToString() {
        String name = "test product";
        product.setName(name);
        assertEquals(name, product.toString());
    }
    @Test
    void testEquals() {
        product.setId(1L);
        Product productTwo = new Product();
        productTwo.setId(1L);
        assertEquals(product, productTwo);
    }
    @Test
    void testHashCode() {
        product.setId(1L);
        Product productTwo = new Product();
        productTwo.setId(1L);
        assertEquals(product.hashCode(), productTwo.hashCode());
    }
}