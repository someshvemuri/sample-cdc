package com.learning.cdc.debezium.db.kafka.impl.mapper;

import com.learning.cdc.debezium.db.kafka.impl.api.dtos.ProductRequest;
import com.learning.cdc.debezium.db.kafka.impl.api.dtos.ProductResponse;
import com.learning.cdc.debezium.db.kafka.impl.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface ProductResourceMapper {

    Product mapAsProduct(ProductRequest productRequest);

    ProductResponse mapAsProductResponse(Product product);
}
