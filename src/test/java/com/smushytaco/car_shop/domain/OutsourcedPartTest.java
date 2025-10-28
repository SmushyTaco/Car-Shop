package com.smushytaco.car_shop.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OutsourcedPartTest {
    OutsourcedPart outsourcedPart;
    @BeforeEach
    void setUp() { outsourcedPart = new OutsourcedPart(); }
    @Test
    void companyName() {
        String companyName = "test company name";
        outsourcedPart.setCompanyName(companyName);
        assertEquals(companyName, outsourcedPart.getCompanyName());
    }
}