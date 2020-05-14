package pl.grupa33inf.ssi.data_store.api;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface INodeDataStore {
    /**
     * Odczytuje jedną zmienną o konkretnej nazwie z konkretnego urządzenia
     * @param deviceUUID Id urządzenia, dla którego odczytywana jest zmienna
     * @param variableName Nazwa zmiennej, która jest odczytywana
     * @return Optional zawierający odczytaną zmienną jeśli taka zmienna istnieje.
     * W przeciwnym przypadku zwraca pusty Optional
     */
    public NodeVariable readVariable(UUID deviceUUID, String
            variableName);
    /**
     * Odczytuje wszystkie zmienne z danego urządzenia.
     * @param deviceUUID Id urządzenia, dla którego odczytywane są zmienne
     * @return Lista zawierająca wszystkie zmienne
     */
    public List<NodeVariable> readAllVariables(UUID deviceUUID);
    /**
     * Odczytuje zmienną z danego urządzenia w danym przedziale czesowym
     * @param from Początek przedziału czasowego
     * @param to Koniec przedziału czasowego
     * @see #readVariable(UUID, String)
     * @return
     */
    public NodeVariable readVariable(UUID deviceUUID, String
            variableName, LocalDateTime from, LocalDateTime to);
    /**
     * Odczytuje wszystkie zmienne z danego urządzenia w danym przedziale czesowym
     * @param from Początek przedziału czasowego
     * @param to Koniec przedziału czasowego
     * @see #readAllVariables(UUID)
     */
    public List<NodeVariable> readAllVariables(UUID deviceUUID, LocalDateTime
            from, LocalDateTime to);
    /**
     * Nadpisuje daną zmienną konfiguracyjną na urządzeniu
     * @param deviceUUID UUID urządzenia na którym zostanie zapisana zmienna
     * @param variableName Nazwa zmiennej do zapisania
     * @param value Wartość do zapisania
     * @return Resultat zapisu
     */
    public Result writeVariable(UUID deviceUUID, String variableName, String value);
    /**
     * Usuwa zmienną z urządzenia i oznacza ją jako usuniętą w bazie danych
     * @param deviceUUID Urządzenie z którego zostanie usunięta zmienna
     * @param variableName Nazwa zmiennej do usunięcia
     * @return Resultat usunięcia
     */
    public Result deleteVariable(UUID deviceUUID, String variableName);
}

