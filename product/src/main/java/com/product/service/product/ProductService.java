package com.product.service.product;

import com.product.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    List<Product> getAllProduct() ;
    Optional<Product> getOneProduct(String ProductId);
    int deleteProduct(Product productId);
    int updateProduct( Product product);
    void createProduct(Product product);
}
