package com.learning.cdc.debezium.db.kafka.impl.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long id) {
        super("No product found with id: " + id);
    }
}