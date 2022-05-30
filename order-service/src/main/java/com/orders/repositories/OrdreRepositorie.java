package com.orders.repositories;

import com.orders.entities.OrderEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdreRepositorie extends JpaRepository<OrderEntitie, Long> {
    OrderEntitie findByUuid(UUID categoryId);
}
