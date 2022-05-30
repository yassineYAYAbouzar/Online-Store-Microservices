package com.product.service.categorie;

import com.product.entities.Categorie;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Categorie> getAllCategories() ;
    Optional<Categorie> getOneCategorie(String categoryId) ;
    Optional<Categorie> getOneCategorieByName(String categoryId) ;
    int deleteCategorie(Categorie categoryId) ;
    int updateCategorie(Categorie categorie);
    void createCategorie(Categorie categorie);
}
