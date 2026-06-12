package com.learning.cdc.debezium.db.kafka.impl.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMessage {

    @JsonProperty("before")
    private Before before;
    @JsonProperty("after")
    private After after;
    @JsonProperty("source")
    private Source source;

    @JsonProperty("op")
    private String operation;

    @JsonProperty("ts_ms")
    private Long ts;

    @JsonProperty("transaction")
    private Object transaction;
}
