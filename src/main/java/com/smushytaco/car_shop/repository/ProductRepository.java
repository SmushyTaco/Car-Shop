package com.smushytaco.car_shop.repository;

import com.smushytaco.car_shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> search(@Param("keyword") final String keyword);
    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.parts WHERE p.id = :id")
    Optional<Product> findByIdWithParts(@Param("id") final long id);
}