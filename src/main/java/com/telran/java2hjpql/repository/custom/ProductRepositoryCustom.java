package com.telran.java2hjpql.repository.custom;

import com.telran.java2hjpql.dto.ProductRequest;
import com.telran.java2hjpql.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    List<Product> searchByRequestObject(ProductRequest searchRequest);
}
