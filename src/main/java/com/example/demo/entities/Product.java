package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Product {  //parent entity

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;


    String productName;


    long quantity;



    @ManyToMany(mappedBy = "products" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Vendor> vendors;





//    @ManyToOne  // for many products there should be one vendor
//    @JoinColumn(name = "vendor_id")
//    private Vendor vendor;
//








//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
//    private Vendor vendor;


//    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
//    private Vendor vendor;













//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long productId;
//
//    String productName;
//
//    long quantity;
//
//    @ManyToMany(mappedBy = "products" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    List<Vendor> vendors;
//
//
//    @ManyToOne
//    @JoinColumn(name  = "category_id" )
//    Category category;

}
