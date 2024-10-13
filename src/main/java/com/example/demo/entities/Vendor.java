package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Vendor {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long vendorId;

    String vendorName;





    @JoinTable(name = "vendor_product",
            joinColumns = @JoinColumn(name = "vendor_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    @ManyToMany (fetch = FetchType.EAGER)
    List<Product> products;






//    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
//    private List<Product> products;
//







//    @OneToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
















//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private List<Product> products;


//
//    @JoinTable(name = "vendor_product",
//            joinColumns = @JoinColumn(name = "vendor_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
//    @ManyToMany (fetch = FetchType.EAGER)
//    List<Product> products;
//
//
//    @OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
//    List<Purchase> purchases;



}
