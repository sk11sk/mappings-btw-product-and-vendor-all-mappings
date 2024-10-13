package com.example.demo.dto;

import com.example.demo.entities.Product;

import java.util.List;//package com.example.demo.dto;


import jakarta.persistence.*;
import lombok.Data;

@Data
public class VendorDTO {

    private Long vendorId;
    private String vendorName;

    private List<ProductDTO> products;



}
