package pl.grupa33inf.ssi.data_store.api;

import lombok.Data;

@Data
public class LogSaveResult<T extends LogEntry> {
    private String databaseMessage;
    private Result result;
    private T entity;
}
