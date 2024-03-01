package com.smushytaco.car_shop.service;
import com.smushytaco.car_shop.domain.OutsourcedPart;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.repository.OutsourcedPartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class OutsourcedPartServiceImpl implements OutsourcedPartService {
    private final OutsourcedPartRepository outsourcedPartRepository;
    public OutsourcedPartServiceImpl(final OutsourcedPartRepository outsourcedPartRepository) { this.outsourcedPartRepository = outsourcedPartRepository; }
    @Override
    @Transactional(readOnly = true)
    public List<OutsourcedPart> findAll() { return outsourcedPartRepository.findAll(); }
    @Override
    @Transactional(readOnly = true)
    public OutsourcedPart findById(final long id) { return outsourcedPartRepository.findById(id).orElseThrow(() -> new RuntimeException("Did not find outsourced part id - " + id)); }
    @Override
    @Transactional
    public OutsourcedPart save(final OutsourcedPart outsourcedPart) { return outsourcedPartRepository.save(outsourcedPart); }
    @Override
    @Transactional(readOnly = true)
    public List<OutsourcedPart> listAll(final String keyword) {
        if (keyword != null) return outsourcedPartRepository.search(keyword);
        return outsourcedPartRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Set<Product> getProducts(final long id) {
        Optional<OutsourcedPart> outsourcedPart = outsourcedPartRepository.findByIdWithProducts(id);
        return outsourcedPart.map(OutsourcedPart::getProducts).orElse(Collections.emptySet());
    }
}