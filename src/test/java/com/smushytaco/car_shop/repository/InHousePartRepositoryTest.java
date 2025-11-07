package com.smushytaco.car_shop.repository;

import com.smushytaco.car_shop.domain.InHousePart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

final class InHousePartRepositoryTest {
    InHousePartRepository inhousePartRepository;
    @BeforeEach
    void setUp() { inhousePartRepository = mock(InHousePartRepository.class); }
    @Test
    void findAll() {
        final InHousePart inHousePart = new InHousePart();
        final List<InHousePart> partData = new ArrayList<>();
        partData.add(inHousePart);
        when(inhousePartRepository.findAll()).thenReturn(partData);
        assertEquals(1, partData.size());
    }
}