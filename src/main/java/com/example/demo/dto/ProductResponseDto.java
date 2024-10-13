package com.example.demo.dto;


//import com.example.demo.entities.Category;
import com.example.demo.entities.Vendor;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponseDto {

    Long productId;

    String productName;

    long quantity;


    List <VendorResponseDto> vendors;





}
