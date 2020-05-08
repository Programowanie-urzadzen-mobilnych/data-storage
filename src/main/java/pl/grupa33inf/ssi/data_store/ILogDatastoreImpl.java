package pl.grupa33inf.ssi.data_store;

import android.content.Context;
import android.widget.Toast;

import pl.grupa33inf.ssi.data_store.api.ILogDatastore;
import pl.grupa33inf.ssi.data_store.api.LogEntry;
import pl.grupa33inf.ssi.data_store.api.LogSaveResult;

class ILogDatastoreImpl implements ILogDatastore {
    @Override
    public <T extends LogEntry> LogSaveResult<T> save(T entry) {

        return null;
    }
}
