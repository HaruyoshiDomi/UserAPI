package com.example.UserAPI.repository;

import com.example.UserAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
