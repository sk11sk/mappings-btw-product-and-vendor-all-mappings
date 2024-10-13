package com.example.demo.dto;


import lombok.Data;

import java.util.List;

@Data
public class VendorProductsDto {



    private Long vendorId;


    private String vendorName;

    List<ProductDTO> productList;



}
