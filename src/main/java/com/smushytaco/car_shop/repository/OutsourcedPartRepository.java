package com.smushytaco.car_shop.repository;
import com.smushytaco.car_shop.domain.OutsourcedPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface OutsourcedPartRepository extends JpaRepository<OutsourcedPart, Long> {
    @Query("SELECT p FROM OutsourcedPart p WHERE p.name LIKE %:keyword%")
    List<OutsourcedPart> search(@Param("keyword") final String keyword);
    @Query("SELECT p FROM OutsourcedPart p LEFT JOIN FETCH p.products WHERE p.id = :id")
    Optional<OutsourcedPart> findByIdWithProducts(@Param("id") final long id);
}