package pl.grupa33inf.ssi.data_store;

import lombok.Getter;
import pl.grupa33inf.ssi.data_store.api.ExceptionLogEntry;
import pl.grupa33inf.ssi.data_store.api.ILogDatastore;
import pl.grupa33inf.ssi.data_store.api.INodeDataStore;

public final class DataStoreServices {
    private static DataStoreServices instance = null;
    @Getter
    private ILogDatastore logDatastore;
    @Getter
    private INodeDataStore nodeDataStore;

    private DataStoreServices() {
        logDatastore = new ILogDatastoreImpl();
        nodeDataStore = new INodeDataStoreImpl();

    }

    public static DataStoreServices getInstance() {
        if (instance == null) {
            instance = new DataStoreServices();
        }

        return instance;
    }
}
