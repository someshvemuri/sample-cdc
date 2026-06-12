package com.learning.cdc.debezium.db.kafka.impl.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.cdc.debezium.db.kafka.impl.dto.ProductMessage;
import org.apache.kafka.common.errors.SerializationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

@Slf4j
public class ProductMessageDeserializer implements Deserializer<ProductMessage> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ProductMessage deserialize(String s, byte[] bytes) {

        if (bytes == null || bytes.length == 0) {
            return null;
        }

        try {
            return objectMapper.readValue(bytes, ProductMessage.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new SerializationException("Unable to deserialize ProductMessage", e);
        }
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ProductMessage deserialize(String topic, Headers headers, byte[] data) {
        return deserialize(topic, data);
    }

    @Override
    public ProductMessage deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
