package com.learning.cdc.debezium.db.kafka.impl.service;

import com.learning.cdc.debezium.db.kafka.impl.entity.Product;
import com.learning.cdc.debezium.db.kafka.impl.exception.ProductNotFoundException;
import com.learning.cdc.debezium.db.kafka.impl.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product currentProduct = getProduct(id);
        currentProduct.setName(product.getName());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setStock(product.getStock());

        return productRepository.save(currentProduct);
    }

    public void deleteProduct(Long id) {
        Product currentProduct = getProduct(id);
        productRepository.delete(currentProduct);
    }
}
