package com.telran.java2hjpql.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductRequest {

    private String seller;
    private String product;
    private Double price;
}
