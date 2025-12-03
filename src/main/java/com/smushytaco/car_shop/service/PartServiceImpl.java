package com.smushytaco.car_shop.service;

import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public final class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    public PartServiceImpl(final PartRepository partRepository) { this.partRepository = partRepository; }
    @Override
    @Transactional(readOnly = true)
    public List<Part> findAll() { return partRepository.findAll(); }
    @Override
    @Transactional(readOnly = true)
    public Part findById(final long id) { return partRepository.findById(id).orElseThrow(() -> new RuntimeException("Did not find part id - " + id)); }
    @Override
    @Transactional
    public Part save(final Part part) { return partRepository.save(part); }
    @Override
    @Transactional
    public void deleteById(final long id) { partRepository.deleteById(id); }
    @Override
    @Transactional(readOnly = true)
    public Set<Product> getProducts(final long id) {
        final Optional<Part> part = partRepository.findByIdWithProducts(id);
        return part.map(thePart -> thePart.products).orElse(Collections.emptySet());
    }
}