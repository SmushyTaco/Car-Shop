package com.smushytaco.car_shop.repository;

import com.smushytaco.car_shop.domain.InHousePart;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InHousePartRepository extends JpaRepository<@NonNull InHousePart, @NonNull Long> {
    @Query("SELECT p FROM InHousePart p WHERE p.name LIKE %:keyword%")
    List<InHousePart> search(@Param("keyword") final String keyword);
    @Query("SELECT p FROM InHousePart p LEFT JOIN FETCH p.products WHERE p.id = :id")
    Optional<InHousePart> findByIdWithProducts(@Param("id") final long id);
}