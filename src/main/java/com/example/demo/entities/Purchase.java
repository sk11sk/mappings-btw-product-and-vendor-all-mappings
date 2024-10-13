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
//@Table(name = "purchases")
//public class Purchase {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long purchaseId;
//
//    Long productId;
//
//
//    long purchasedQuantity;
//
//
//
//
//
//    @ManyToOne
//    @JoinColumn(name  = "vendor_id" )
//    Vendor vendor;
//
//}
