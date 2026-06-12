package com.learning.cdc.debezium.db.kafka.impl.controller;

import com.learning.cdc.debezium.db.kafka.impl.api.ProductsApi;
import com.learning.cdc.debezium.db.kafka.impl.api.dtos.ProductRequest;
import com.learning.cdc.debezium.db.kafka.impl.api.dtos.ProductResponse;
import com.learning.cdc.debezium.db.kafka.impl.entity.Product;
import com.learning.cdc.debezium.db.kafka.impl.mapper.ProductResourceMapper;
import com.learning.cdc.debezium.db.kafka.impl.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController implements ProductsApi {

    private final ProductService productService;
    private final ProductResourceMapper productResourceMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "id") Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(productResourceMapper.mapAsProductResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts().stream()
                .map(productResourceMapper::mapAsProductResponse)
                .toList();
        return ResponseEntity.ok(products);
    }

    @Override
    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productResourceMapper.mapAsProduct(productRequest);
        Product productResponse = productService.createProduct(product);
        ProductResponse response = productResourceMapper.mapAsProductResponse(productResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(name = "id") Long id,
                                                         @Valid @RequestBody ProductRequest productRequest) {
        Product product = productResourceMapper.mapAsProduct(productRequest);
        Product productResponse = productService.updateProduct(id, product);
        return ResponseEntity.ok(productResourceMapper.mapAsProductResponse(productResponse));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
