package com.product.entities;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String name ;
    private String description;
    private String image ;
    private Float prix ;
    @Transient
    private String categorieName;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


}
