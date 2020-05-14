package pl.grupa33inf.ssi.data_store.api;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogEntry {
    /**
     * Czas wystąpienia wydażenia
     */
    private LocalDateTime timestamp;
    /**
     * Jednoznaczny identyfikator telefonu
     */
    private String phoneIdentifier;
    /**
     * Typ wydażenia
     */
    private EntryType type;
}
