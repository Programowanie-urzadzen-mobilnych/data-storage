package pl.grupa33inf.ssi.data_store.api;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NodeValue {
    private String value;
    private LocalDateTime timestamp;
}