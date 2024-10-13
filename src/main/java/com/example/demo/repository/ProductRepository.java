package com.example.demo.repository;


import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product, Long> {

  Optional<Product> findByProductName(String productName);

 //  List<Product>  findByProductName(String productName);
 @Query("SELECT SUM(p.quantity) FROM Product p WHERE p.productName = :productName")
 Long getTotalQuantityByProductName(@Param("productName") String productName);


}
