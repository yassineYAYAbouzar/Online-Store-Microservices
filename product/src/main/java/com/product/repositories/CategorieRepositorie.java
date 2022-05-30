package com.product.repositories;

import com.product.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategorieRepositorie extends JpaRepository<Categorie, Long> {
    Categorie findByUuid(UUID categoryId);
    Categorie findByName(String categoryId);
}
