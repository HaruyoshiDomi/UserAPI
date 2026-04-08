package com.example.UserAPI.service;

import com.example.UserAPI.service.ProductService;
import com.example.UserAPI.dto.ProductResponse;
import com.example.UserAPI.model.Product;
import com.example.UserAPI.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    //コンストラクタインジェクション
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //商品一覧
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
        .map(product -> new ProductResponse(
            product.getId(), 
            product.getName(), 
            product.getPrice()))
        .collect(Collectors.toList());
    }
    
    //商品詳細
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        
        return new ProductResponse(
                        product.getId(), 
                        product.getName(), 
                        product.getPrice());
    }
}
