package com.product.repositories;

import com.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepositorie extends JpaRepository<Product , Long> {
     Product findByUuid(UUID uuid);
}
