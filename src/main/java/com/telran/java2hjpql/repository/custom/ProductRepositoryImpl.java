package com.telran.java2hjpql.repository.custom;

import com.telran.java2hjpql.dto.ProductRequest;
import com.telran.java2hjpql.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Product> searchByRequestObject(ProductRequest searchRequest) {

        StringBuilder query = new StringBuilder();
        Map<String, Object> parameters = new HashMap<>();

        query.append("select p from Product p where 1=1 ");

        //product, seller

        if (searchRequest.getPrice() != null) {
            query.append(" and p.price = :price ");
            parameters.put("price", searchRequest.getPrice());
        }

        if (searchRequest.getProduct() != null) {
            query.append(" and p.productName = :productName ");
            parameters.put("productName", searchRequest.getProduct());
        }

        if (searchRequest.getSeller() != null) {
            query.append(" and p.sellerName = :seller");
            parameters.put("seller", searchRequest.getSeller());
        }

        Query jpaQuery = entityManager.createQuery(query.toString());

        for (Map.Entry<String, Object> map : parameters.entrySet()) {
            jpaQuery.setParameter(map.getKey(), map.getValue());
        }


        return jpaQuery.getResultList(); //List = List<Object>
    }
}
