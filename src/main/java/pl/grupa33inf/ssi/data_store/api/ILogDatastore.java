package pl.grupa33inf.ssi.data_store.api;

public interface ILogDatastore {
    /**
     * Zapisuje dane wydażenie do bazy danych.
     * @param entry Wydażenie, które należy zapisać do bazy
     * @return Resultat zapisu do bazy
     */
    public <T extends LogEntry> LogSaveResult<T> save(T entry);
}
