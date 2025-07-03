package com.e_bal.e_bal_product_service.mappers;

import com.e_bal.e_bal_product_service.dtos.ProductDTO;
import com.e_bal.e_bal_product_service.models.Product;
import com.e_bal.e_bal_product_service.models.ProductImage;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        List<String> imageUrls = product.getImages()
                .stream()
                .map(ProductImage::getUrl)
                .collect(Collectors.toList());

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                imageUrls
        );
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setCategory(dto.getCategory());

        if (dto.getImageUrls() != null) {
            dto.getImageUrls().forEach(url -> product.addImage(url));
        }

        return product;
    }
}
