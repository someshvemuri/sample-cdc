package com.learning.cdc.debezium.db.kafka.impl.kafka;

import com.learning.cdc.debezium.db.kafka.impl.dto.ProductMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "${app.kafka.group-id}",
            containerFactory = "productKafkaListenerContainerFactory"
    )
    public void consume(ProductMessage productMessage) {
        log.info("Consumed message {}", productMessage);

        if (productMessage == null || productMessage.getOperation() == null) {
            log.warn("Received empty Debezium message");
            return;
        }

        Long productId = extractProductId(productMessage);

        switch (productMessage.getOperation()) {
            case "c" -> log.info("Create event received for product id={}", productId);
            case "u" -> log.info("Update event received for product id={}", productId);
            case "d" -> log.info("Delete event received for product id={}", productId);
            default -> log.warn("Unsupported CDC operation {} for product id={}",
                    productMessage.getOperation(), productId);
        }
    }

    private Long extractProductId(ProductMessage productMessage) {
        if (productMessage.getAfter() != null) {
            return productMessage.getAfter().getId();
        }
        if (productMessage.getBefore() != null) {
            return productMessage.getBefore().getId();
        }
        return null;
    }
}
