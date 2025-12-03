package com.smushytaco.car_shop.domain;

import com.smushytaco.car_shop.validator.EnoughPartsValidator;
import com.smushytaco.car_shop.validator.PriceProductValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import manifold.ext.props.rt.api.var;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
@PriceProductValidator
@EnoughPartsValidator
@SuppressWarnings("JpaDataSourceORMInspection")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @var
    long id;
    @NotBlank(message = "Name cannot be blank.")
    @Column(nullable = false)
    @var
    String name;
    @Min(value = 0, message = "Price value must be positive.")
    @Column(nullable = false)
    @var
    double price;
    @Min(value = 0, message = "Inventory value must be positive.")
    @Column(nullable = false)
    @var
    int inv;
    @ManyToMany(mappedBy = "products", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @var
    Set<Part> parts = new HashSet<>();
    public Product() {}
    public Product(final String name, final double price, final int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }
    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Product product = (Product) o;
        return id == product.id;
    }
    @Override
    public final int hashCode() { return Objects.hash(id); }
    @Override
    public final String toString() { return name; }
}