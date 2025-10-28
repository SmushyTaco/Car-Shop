package com.smushytaco.car_shop.service;

import com.smushytaco.car_shop.domain.InHousePart;
import com.smushytaco.car_shop.repository.InHousePartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InHousePartServiceTest {
    InHousePartRepository inhousePartRepository;
    InHousePartService inhousePartService;
    @BeforeEach
    void setUp() {
        inhousePartRepository = mock(InHousePartRepository.class);
        inhousePartService = new InHousePartServiceImpl(inhousePartRepository);
    }
    @Test
    void findAll() {
        InHousePart part = new InHousePart();
        List<InHousePart> partData = new ArrayList<>();
        partData.add(part);
        when(inhousePartRepository.findAll()).thenReturn(partData);
        assertEquals(1, partData.size());
    }
}