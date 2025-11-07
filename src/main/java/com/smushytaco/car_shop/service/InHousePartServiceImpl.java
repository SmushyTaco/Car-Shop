package com.smushytaco.car_shop.service;

import com.smushytaco.car_shop.domain.InHousePart;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.repository.InHousePartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public final class InHousePartServiceImpl implements InHousePartService {
    private final InHousePartRepository inHousePartRepository;
    public InHousePartServiceImpl(final InHousePartRepository inHousePartRepository) { this.inHousePartRepository = inHousePartRepository; }
    @Override
    @Transactional(readOnly = true)
    public List<InHousePart> findAll() { return inHousePartRepository.findAll(); }
    @Override
    @Transactional(readOnly = true)
    public InHousePart findById(final long id) { return inHousePartRepository.findById(id).orElseThrow(() -> new RuntimeException("Did not find in house part id - " + id)); }
    @Override
    @Transactional
    public InHousePart save(final InHousePart inHousePart) { return inHousePartRepository.save(inHousePart); }
    @Override
    @Transactional(readOnly = true)
    public List<InHousePart> listAll(final String keyword) {
        if (keyword != null) return inHousePartRepository.search(keyword);
        return inHousePartRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Set<Product> getProducts(final long id) {
        final Optional<InHousePart> inHousePart = inHousePartRepository.findByIdWithProducts(id);
        return inHousePart.map(InHousePart::getProducts).orElse(Collections.emptySet());
    }
}