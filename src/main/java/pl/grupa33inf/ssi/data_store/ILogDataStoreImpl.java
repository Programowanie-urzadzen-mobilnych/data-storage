package pl.grupa33inf.ssi.data_store;

import android.util.Log;

import lombok.NoArgsConstructor;
import pl.grupa33inf.ssi.data_store.api.ILogDatastore;
import pl.grupa33inf.ssi.data_store.api.LogEntry;
import pl.grupa33inf.ssi.data_store.api.LogSaveResult;

@NoArgsConstructor
public class ILogDataStoreImpl implements ILogDatastore {
    @Override
    public <T extends LogEntry> LogSaveResult<T> save(T entry) {
        Log.d("ILogDataStore", entry.toString());
        return null;
    }
}
