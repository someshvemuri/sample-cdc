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
public class Source {

    @JsonProperty("name")
    private String name;

    @JsonProperty("ts_ms")
    private Long tsms;

    @JsonProperty("snapshot")
    private boolean snapshot;

    @JsonProperty("schema")
    private String schema;

    @JsonProperty("table")
    private String table;

    @JsonProperty("txId")
    private Integer txId;

    @JsonProperty("lsn")
    private Integer lsn;

    @JsonProperty("xmin")
    private Object xmin;
}
