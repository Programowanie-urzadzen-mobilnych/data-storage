package pl.grupa33inf.ssi.data_store.api;

import lombok.Data;

/**
 * Wydażenie informujące o czymś, niekoniecznie o problemie
 */
@Data
public class InfoLogEntry extends LogEntry {
    /**
     * Główna wiadomość wydażenia
     */
    private String message;
    /**
     * Dodatkowe informacje o zdażeniu
     */
    private String additionalData;
}
