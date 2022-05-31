package com.product.service.product;

import com.product.entities.Categorie;
import com.product.entities.Product;
import com.product.repositories.ProductRepositorie;
import com.product.service.categorie.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@AllArgsConstructor
@Service
public class ProductServiceImp implements ProductService{
    final ProductRepositorie productRepositorie;
    @Override
    public List<Product> getAllProduct() {
        return productRepositorie.findAll();
    }

    @Override
    public Optional<Product> getOneProduct(String ProductId) {

        Product byUuid = productRepositorie
                .findByUuid(UUID.fromString(ProductId));
        byUuid.setCategorie(null);
        return Optional.ofNullable(byUuid);
    }

    @Override
    public int deleteProduct(Product product) {
        try {
            productRepositorie.delete(product);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public int updateProduct(Product product) {
        try {
            productRepositorie.save(product);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public void createProduct(Product product) {
        product.setUuid(UUID.randomUUID());
        productRepositorie.save(product);
    }
}
