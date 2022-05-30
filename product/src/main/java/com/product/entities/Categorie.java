package com.product.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Size(max = 12 , min = 4 , message = "size must be between 4 and 12")
    private String name ;
    private String description;


    @OneToMany(mappedBy = "categorie" ,fetch = FetchType.LAZY)
    Set<Product> products = new HashSet<>();
}
