package com.e_bal.e_bal_product_service.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO used for creating or returning product data through the API.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private String category;

    private List<String> imageUrls;
}
