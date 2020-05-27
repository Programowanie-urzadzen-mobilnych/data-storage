package pl.grupa33inf.ssi.data_store;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.NoArgsConstructor;
import pl.grupa33inf.ssi.data_store.api.INodeDataStore;
import pl.grupa33inf.ssi.data_store.api.NodeValue;
import pl.grupa33inf.ssi.data_store.api.NodeVariable;
import pl.grupa33inf.ssi.data_store.api.Result;

@NoArgsConstructor
public class INodeDataStoreImpl implements INodeDataStore {

    @Override
    public NodeVariable readVariable(UUID deviceUUID, String variableName) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


        return null;
    }

    @Override
    public List<NodeVariable> readAllVariables(UUID deviceUUID) {
        return new ArrayList<>();
    }

    @Override
    public NodeVariable readVariable(UUID deviceUUID, String variableName, LocalDateTime from, LocalDateTime to) {
        return null;
    }

    @Override
    public List<NodeVariable> readAllVariables(UUID deviceUUID, LocalDateTime from, LocalDateTime to) {
        return new ArrayList<>();
    }

    @Override
    public Result writeVariable(UUID deviceUUID, String variableName, String value) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference variable = mDatabase.child("nodes").child(deviceUUID.toString()).child(variableName);
        variable.child("value").setValue(value);
        variable.child("history").push().setValue(NodeValue.builder()
                .value(value)
                .timestamp(new Date())
                .build()
        );

        Log.d("INodeDataStore", String.format("%s, %s, %s", deviceUUID.toString(), variableName, value));
        return Result.OK;
    }

    @Override
    public Result deleteVariable(UUID deviceUUID, String variableName) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference variable = mDatabase.child("nodes").child(deviceUUID.toString()).child(variableName);
        variable.child("deleted").setValue(true);
        return Result.OK;
    }
}
