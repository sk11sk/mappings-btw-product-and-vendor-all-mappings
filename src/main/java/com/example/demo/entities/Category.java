//package com.example.demo.entities;
//
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//@Entity
//public class Category {
//
//
//    @Id
//    Long categoryId;
//
//    String categoryName;
//
//
//
//    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
//    List<Product> products;
//
//
//}
