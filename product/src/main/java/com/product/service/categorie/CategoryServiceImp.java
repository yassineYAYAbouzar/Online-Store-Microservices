package com.product.service.categorie;

import com.product.entities.Categorie;
import com.product.repositories.CategorieRepositorie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService{
    private final CategorieRepositorie categorieRepositorie;
    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepositorie.findAll();
    }

    @Override
    public Optional<Categorie> getOneCategorie(String categoryId) {
        return Optional.ofNullable(categorieRepositorie
                .findByUuid(UUID.fromString(categoryId)));
    }

    @Override
    public Optional<Categorie> getOneCategorieByName(String categoryId) {
        return Optional.ofNullable(categorieRepositorie
                .findByName(categoryId));
    }

    @Override
    public int deleteCategorie(Categorie categorie)  {
        try {
            categorieRepositorie.delete(categorie);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int updateCategorie( Categorie newCategorie) {
        try {
            categorieRepositorie.save(newCategorie);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public void createCategorie(Categorie categorie) {
        categorie.setUuid(UUID.randomUUID());
        categorieRepositorie.save(categorie);
    }
}
