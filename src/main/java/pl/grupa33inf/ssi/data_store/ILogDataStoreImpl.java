package pl.grupa33inf.ssi.data_store;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lombok.NoArgsConstructor;
import pl.grupa33inf.ssi.data_store.api.ILogDatastore;
import pl.grupa33inf.ssi.data_store.api.LogEntry;

@NoArgsConstructor
public class ILogDataStoreImpl implements ILogDatastore {
    @Override
    public void save(LogEntry entry) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("logs").push().setValue(entry);
    }
}
