package com.e_bal.e_bal_product_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_bal.e_bal_product_service.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
