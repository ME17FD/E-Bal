package com.e_bal.e_bal_product_service.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.e_bal.e_bal_product_service.dtos.ProductDTO;
import com.e_bal.e_bal_product_service.mappers.ProductMapper;
import com.e_bal.e_bal_product_service.models.Product;
import com.e_bal.e_bal_product_service.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductMapper::toDTO).orElse(null);
    }

    public ProductDTO createProduct(ProductDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
