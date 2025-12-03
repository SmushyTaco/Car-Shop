package com.smushytaco.car_shop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class OutsourcedPartTest {
    OutsourcedPart outsourcedPart;
    @BeforeEach
    void setUp() { outsourcedPart = new OutsourcedPart(); }
    @Test
    void companyName() {
        final String companyName = "test company name";
        outsourcedPart.companyName = companyName;
        assertEquals(companyName, outsourcedPart.companyName);
    }
}