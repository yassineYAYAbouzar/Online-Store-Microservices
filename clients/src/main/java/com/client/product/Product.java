package com.client.product;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private Long id;
    private UUID uuid;
    private String name ;
    private String description;
    private String image ;
    private Float prix ;
    private String categorieName;


}
