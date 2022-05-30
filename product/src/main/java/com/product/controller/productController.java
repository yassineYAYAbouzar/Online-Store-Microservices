package com.product.controller;

import com.product.entities.Categorie;
import com.product.entities.Product;
import com.product.service.categorie.CategoryService;
import com.product.service.product.ProductServiceImp;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class productController {
    final ProductServiceImp productServiceImp;
    final CategoryService categoryService;
    @GetMapping("")
    public List<Product> getAllProduct(){
        List<Product> products =productServiceImp.getAllProduct();
        products.forEach(product -> {
            product.setCategorieName(product.getCategorie().getName());
            product.setCategorie(null);
        });
        return products;
    }
    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody Product product) throws Exception {
        Optional<Categorie> oneCategorieByName = categoryService.getOneCategorieByName(product.getCategorieName());

        if(oneCategorieByName.isPresent()){
            product.setCategorie(oneCategorieByName.get());
            productServiceImp.createProduct(product);
            product.setCategorie(null);
            return new ResponseEntity<Product>( product , HttpStatus.CREATED) ;
        }
        return ResponseEntity.badRequest().body(new NoProduct("not Catgory Yeat !") );

    }

    @PatchMapping(path = "/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable String productId , @RequestBody Product product) throws Exception{
        productServiceImp.updateProduct(product);
        Optional<Product> oneProduct = productServiceImp.getOneProduct(productId);
        if(oneProduct.isPresent()){
            productServiceImp.updateProduct(oneProduct.get());
            return new ResponseEntity<Product>( product , HttpStatus.ACCEPTED) ;
        }
        return ResponseEntity.badRequest().body(new NoProduct("not product Yeat !") );

    }
    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId ) {
        Optional<Product> oneProduct = productServiceImp.getOneProduct(productId);
        if(oneProduct.isPresent()){
            productServiceImp.deleteProduct(oneProduct.get());
            return  new ResponseEntity<Product>(  HttpStatus.NO_CONTENT) ;
        }
        return ResponseEntity.badRequest().body(new NoProduct("not product Yeat !") );
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable String productId )  {
        Optional<Product> oneProduct = productServiceImp.getOneProduct(productId);
        if(oneProduct.isPresent()){
            return  new ResponseEntity<Product>( oneProduct.get() , HttpStatus.OK) ;
        }
        return ResponseEntity.badRequest().body(new NoProduct("not product Yeat !") );

    }
}
@AllArgsConstructor @Data
class NoProduct{
    final String message;
}