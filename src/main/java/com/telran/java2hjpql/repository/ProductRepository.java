package com.telran.java2hjpql.repository;

import com.telran.java2hjpql.entity.Product;
import com.telran.java2hjpql.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query("select p from Product p")
    List<Product> getAllProducts();

    @Query("select p.productName from Product p")
    List<String> getAllProductNames();

    //JPQL parameters = Map<String, Object> (String = param name, Object = param value)
    @Query("select p from Product p where p.productName = :productName and" +
            " p.price = :price and " +
            " p.sellerName = :sellerName")
    List<Product> getAllProductsByAllParams(@Param("productName") String productName,
                                       @Param("price") Double price,
                                       @Param("sellerName") String sellerName);

    @Query("select p from Product p where p.productName = :productName")
    List<Product> getAllProductsByProductName(@Param("productName") String productName);

    @Query("select p from Product p where p.price between :minPrice and :maxPrice")
    List<Product> getAllProductsWithPriceBetween(@Param("minPrice") Double min,
                                                  @Param("maxPrice") Double max);

    @Query("select p from Product p where p.productName like %:productName%")
    List<Product> getAllProductsByNameLike(@Param("productName") String productName);
}
