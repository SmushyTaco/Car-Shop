package com.smushytaco.car_shop.domain;

import com.smushytaco.car_shop.validator.DeleteOrUpdatePartValidator;
import com.smushytaco.car_shop.validator.MaximumInventoryPartValidator;
import com.smushytaco.car_shop.validator.MinimumInventoryPartValidator;
import com.smushytaco.car_shop.validator.MinimumMaximumInventoryPartValidator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@DeleteOrUpdatePartValidator
@MinimumInventoryPartValidator
@MaximumInventoryPartValidator
@MinimumMaximumInventoryPartValidator
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "part_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "parts")
@SuppressWarnings("JpaDataSourceORMInspection")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @NotBlank(message = "Name cannot be blank.")
    @Column(nullable = false)
    private String name;
    @Min(value = 0, message = "Price value must be positive.")
    @Column(nullable = false)
    private double price;
    @Min(value = 0, message = "Inventory value must be positive.")
    @Column(nullable = false)
    private int inv;
    @Min(value = 0, message = "Minimum inventory value must be positive.")
    @Column(nullable = false)
    private int minInv;
    @Min(value = 0, message = "Maximum inventory value must be positive.")
    @Column(nullable = false)
    private int maxInv;
    @ManyToMany
    @JoinTable(name = "product_part", joinColumns = @JoinColumn(name="part_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();
    protected Part() {}
    protected Part(final String name, final double price, final int inv, final int minInv, final int maxInv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
        this.minInv = minInv;
        this.maxInv = maxInv;
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return id == part.id;
    }
    @Override
    public int hashCode() { return Objects.hash(id); }
    @Override
    public String toString() { return name; }
}