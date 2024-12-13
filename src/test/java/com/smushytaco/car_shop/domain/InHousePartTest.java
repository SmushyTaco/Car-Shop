package com.smushytaco.car_shop.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class InHousePartTest {
    InHousePart inHousePart;
    @BeforeEach
    void setUp() { inHousePart = new InHousePart(); }
    @Test
    void partId() {
        int partId = 4;
        inHousePart.setPartId(partId);
        assertEquals(partId, inHousePart.getPartId());
    }
}