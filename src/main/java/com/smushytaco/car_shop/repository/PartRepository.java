package com.smushytaco.car_shop.repository;

import com.smushytaco.car_shop.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Long> {
    @Query("SELECT p FROM Part p LEFT JOIN FETCH p.products WHERE p.id = :id")
    Optional<Part> findByIdWithProducts(@Param("id") final long id);
}