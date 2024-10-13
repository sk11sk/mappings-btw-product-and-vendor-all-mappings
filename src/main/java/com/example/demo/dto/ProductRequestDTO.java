package com.example.demo.dto;


import com.example.demo.entities.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductRequestDTO {


private Long categoryId;

Long vendorId;


   //long purchasedQuantity;

    String productName;

    long quantity;


//List<Product> productList;


}
