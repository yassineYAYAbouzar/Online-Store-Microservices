package com.product.controller;

import com.product.entities.Categorie;
import com.product.service.categorie.CategoryServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class CategorieController {
    private final CategoryServiceImp categoryServiceImp;
    @GetMapping("")
    public List<Categorie> getAllCategories(){
        List<Categorie> categorieList =categoryServiceImp.getAllCategories();
        return categorieList;
    }
    @PostMapping("")
    public ResponseEntity<Categorie> createUser(@RequestBody @Valid Categorie categorie) throws Exception {
        categoryServiceImp.createCategorie(categorie);
        return new ResponseEntity<Categorie>( categorie , HttpStatus.CREATED) ;
    }

    @PatchMapping(path = "/{categoryId}")
    public ResponseEntity<?> updateCategorie(@PathVariable String categoryId , @RequestBody Categorie categorie) throws Exception{
        categoryServiceImp.updateCategorie(categorie);
        Optional<Categorie> oneCategorie = categoryServiceImp.getOneCategorie(categoryId);
        if(oneCategorie.isPresent()){
            categoryServiceImp.updateCategorie(oneCategorie.get());
            return new ResponseEntity<Categorie>( categorie , HttpStatus.ACCEPTED) ;
        }
        return ResponseEntity.badRequest().body(new NotCategory("not category Yeat !") );

    }
    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<?> deleteCategorie(@PathVariable String categoryId ) {
        Optional<Categorie> oneCategorie = categoryServiceImp.getOneCategorie(categoryId);
        if(oneCategorie.isPresent()){
            categoryServiceImp.deleteCategorie(oneCategorie.get());
            return  new ResponseEntity<Categorie>(  HttpStatus.NO_CONTENT) ;
        }
        return ResponseEntity.badRequest().body(new NotCategory("not category Yeat !") );
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<?> getResponsable(@PathVariable String categoryId )  {
        Optional<Categorie> oneCategorie = categoryServiceImp.getOneCategorie(categoryId);
        if(oneCategorie.isPresent()){
            return  new ResponseEntity<Categorie>( oneCategorie.get() , HttpStatus.OK) ;
        }
        return ResponseEntity.badRequest().body(new NotCategory("not category Yeat !") );

    }

}
@AllArgsConstructor @Data
class NotCategory{
    final String message;
}