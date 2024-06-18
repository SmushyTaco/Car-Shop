package com.smushytaco.car_shop.repository;
import com.smushytaco.car_shop.domain.InHousePart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class InHousePartRepositoryTest {
    InHousePartRepository inhousePartRepository;
    @BeforeEach
    void setUp() { inhousePartRepository = mock(InHousePartRepository.class); }
    @Test
    void findAll() {
        InHousePart inHousePart = new InHousePart();
        List<InHousePart> partData = new ArrayList<>();
        partData.add(inHousePart);
        when(inhousePartRepository.findAll()).thenReturn(partData);
        assertEquals(1, partData.size());
    }
}