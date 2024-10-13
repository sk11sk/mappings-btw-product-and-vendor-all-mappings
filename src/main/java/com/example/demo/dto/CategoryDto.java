package com.example.demo.dto;


import com.example.demo.entities.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {


    Long categoryId;

    String categoryName;



}
