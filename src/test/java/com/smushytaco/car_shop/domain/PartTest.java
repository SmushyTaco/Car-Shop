package com.smushytaco.car_shop.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
class PartTest {
    Part partIn;
    Part partOut;
    @BeforeEach
    void setUp() {
        partIn = new InHousePart();
        partOut = new OutsourcedPart();
    }
    @Test
    void getId() {
        Long idValue = 4L;
        partIn.setId(idValue);
        assertEquals(partIn.getId(), idValue);
        partOut.setId(idValue);
        assertEquals(partOut.getId(), idValue);
    }
    @Test
    void setId() {
        Long idValue = 4L;
        partIn.setId(idValue);
        assertEquals(partIn.getId(), idValue);
        partOut.setId(idValue);
        assertEquals(partOut.getId(), idValue);
    }
    @Test
    void getName() {
        String name = "test in house part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }
    @Test
    void setName() {
        String name = "test in house part";
        partIn.setName(name);
        assertEquals(name, partIn.getName());
        name="test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.getName());
    }
    @Test
    void getPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }
    @Test
    void setPrice() {
        double price = 1.0;
        partIn.setPrice(price);
        assertEquals(price, partIn.getPrice());
        partOut.setPrice(price);
        assertEquals(price, partOut.getPrice());
    }
    @Test
    void getMinInv() {
        int minInv = 3;
        partIn.setMinInv(minInv);
        assertEquals(minInv, partIn.getMinInv());
        partOut.setMinInv(minInv);
        assertEquals(minInv, partOut.getMinInv());
    }
    @Test
    void setMinInv() {
        int minInv = 3;
        partIn.setMinInv(minInv);
        assertEquals(minInv, partIn.getMinInv());
        partOut.setMinInv(minInv);
        assertEquals(minInv, partOut.getMinInv());
    }
    @Test
    void getMaxInv() {
        int maxInv = 8;
        partIn.setMaxInv(maxInv);
        assertEquals(maxInv, partIn.getMaxInv());
        partOut.setMaxInv(maxInv);
        assertEquals(maxInv, partOut.getMaxInv());
    }
    @Test
    void setMaxInv() {
        int maxInv = 8;
        partIn.setMaxInv(maxInv);
        assertEquals(maxInv, partIn.getMaxInv());
        partOut.setMaxInv(maxInv);
        assertEquals(maxInv, partOut.getMaxInv());
    }
    @Test
    void getInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }
    @Test
    void setInv() {
        int inv = 5;
        partIn.setInv(inv);
        assertEquals(inv, partIn.getInv());
        partOut.setInv(inv);
        assertEquals(inv, partOut.getInv());
    }
    @Test
    void getProducts() {
        Product productOne = new Product();
        Product productTwo = new Product();
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(productOne);
        myProducts.add(productTwo);
        partIn.setProducts(myProducts);
        assertEquals(myProducts, partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }
    @Test
    void setProducts() {
        Product productOne = new Product();
        Product productTwo = new Product();
        Set<Product> myProducts = new HashSet<>();
        myProducts.add(productOne);
        myProducts.add(productTwo);
        partIn.setProducts(myProducts);
        assertEquals(myProducts, partIn.getProducts());
        partOut.setProducts(myProducts);
        assertEquals(myProducts, partOut.getProducts());
    }
    @Test
    void testToString() {
        String name = "test in house part";
        partIn.setName(name);
        assertEquals(name, partIn.toString());
        name = "test outsourced part";
        partOut.setName(name);
        assertEquals(name, partOut.toString());
    }
    @Test
    void testEquals() {
        partIn.setId(1L);
        Part newPartIn = new InHousePart();
        newPartIn.setId(1L);
        assertEquals(partIn, newPartIn);
        partOut.setId(1L);
        Part newPartOut = new OutsourcedPart();
        newPartOut.setId(1L);
        assertEquals(partOut, newPartOut);
    }
    @Test
    void testHashCode() {
        partIn.setId(1L);
        partOut.setId(1L);
        assertEquals(partIn.hashCode(), partOut.hashCode());
    }
}