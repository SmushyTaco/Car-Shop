package com.smushytaco.car_shop.service;

import com.smushytaco.car_shop.domain.Part;
import com.smushytaco.car_shop.domain.Product;
import com.smushytaco.car_shop.repository.PartRepository;
import com.smushytaco.car_shop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private Set<Part> getPartsInternal(final long id) {
        Optional<Product> product = productRepository.findByIdWithParts(id);
        return product.map(Product::getParts).orElse(Collections.emptySet());
    }
    private Product findByIdInternal(final long id) { return productRepository.findById(id).orElseThrow(() -> new RuntimeException(DID_NOT_FIND_PRODUCT + id)); }
    private static final String DID_NOT_FIND_PRODUCT = "Did not find product id - ";
    private final ProductRepository productRepository;
    private final PartRepository partRepository;
    public ProductServiceImpl(final ProductRepository productRepository, final PartRepository partRepository) {
        this.productRepository = productRepository;
        this.partRepository = partRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() { return productRepository.findAll(); }
    @Override
    @Transactional(readOnly = true)
    public Product findById(final long id) { return findByIdInternal(id); }
    @Override
    @Transactional
    public Product save(final Product product) { return productRepository.save(product); }
    @Override
    @Transactional
    public void deleteById(final long id) { productRepository.deleteById(id); }
    @Override
    @Transactional(readOnly = true)
    public List<Product> listAll(final String keyword) {
        if(keyword != null) return productRepository.search(keyword);
        return productRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Set<Part> getParts(final long id) { return getPartsInternal(id); }
    @Override
    @Transactional
    public void associatePartToProduct(final long productId, final long partId) {
        Product product = productRepository.findByIdWithParts(productId).orElseThrow(() -> new RuntimeException(DID_NOT_FIND_PRODUCT + productId));
        Part part = partRepository.findByIdWithProducts(partId).orElseThrow(() -> new RuntimeException("Did not find part id - " + partId));
        product.getParts().add(part);
        part.getProducts().add(product);
        productRepository.save(product);
    }
    @Override
    @Transactional
    public void removePartFromProduct(final long productId, final long partId) {
        Product product = productRepository.findByIdWithParts(productId).orElseThrow(() -> new RuntimeException(DID_NOT_FIND_PRODUCT + productId));
        Part part = partRepository.findByIdWithProducts(partId).orElseThrow(() -> new RuntimeException("Did not find part id - " + partId));
        product.getParts().remove(part);
        part.getProducts().remove(product);
        productRepository.save(product);
        partRepository.save(part);
    }
    @Override
    @Transactional(readOnly = true)
    public boolean productPriceIsValid(final Product product, final Part extraPart) {
        if (extraPart == null) return product.getPrice() >= getPartsInternal(findByIdInternal(product.getId()).getId()).stream().mapToDouble(Part::getPrice).sum();
        return product.getPrice() >= getPartsInternal(findByIdInternal(product.getId()).getId()).stream().mapToDouble(Part::getPrice).sum() + extraPart.getPrice();
    }
}