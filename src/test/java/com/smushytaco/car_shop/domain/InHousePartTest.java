package com.smushytaco.car_shop.domain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
class InHousePartTest {
    InHousePart ip;
    @BeforeEach
    void setUp() { ip = new InHousePart(); }
    @Test
    void getPartId() {
        int idValue = 4;
        ip.setPartId(idValue);
        assertEquals(ip.getPartId(), idValue);
    }
    @Test
    void setPartId() {
        int idValue = 4;
        ip.setPartId(idValue);
        assertEquals(ip.getPartId(), idValue);
    }
}