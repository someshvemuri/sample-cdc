package com.learning.cdc.debezium.db.kafka.impl.repository;

import com.learning.cdc.debezium.db.kafka.impl.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
