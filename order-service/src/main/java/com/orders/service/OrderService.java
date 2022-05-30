package com.orders.service;

import com.orders.entities.OrderEntitie;
import com.orders.repositories.OrdreRepositorie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public record OrderService(OrdreRepositorie ordreRepositorie) {
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
        orderEntitie.setUuid(UUID.randomUUID());
        ordreRepositorie.save(orderEntitie);
    }

}
