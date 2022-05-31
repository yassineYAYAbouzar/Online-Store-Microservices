package com.orders.service;

import com.client.product.Product;
import com.client.product.ProductClient;
import com.orders.entities.OrderEntitie;
import com.orders.repositories.OrdreRepositorie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public record OrderService(OrdreRepositorie ordreRepositorie , ProductClient productClient) {
    public List<OrderEntitie> getAllOrder() {
        return ordreRepositorie.findAll();
    }

    public Optional<OrderEntitie> getOneOrder(String ordrId) {

        return Optional.ofNullable(ordreRepositorie
                .findByUuid(UUID.fromString(ordrId)));
    }


    public int deleteOrder(OrderEntitie orderEntitie) {
        try {
            ordreRepositorie.delete(orderEntitie);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public void createOrder(OrderEntitie orderEntitie) {


        Product product= productClient.getProduct(orderEntitie.getProductId());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        orderEntitie.setBayerId(auth.getName());
        orderEntitie.setProductName(product.getName());
        orderEntitie.setPrix(product.getPrix());
        orderEntitie.setUuid(UUID.randomUUID());
        orderEntitie.setDateComande(LocalDate.now());
        ordreRepositorie.save(orderEntitie);
    }
}
