package pl.grupa33inf.ssi.data_store;

import android.annotation.TargetApi;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import lombok.NoArgsConstructor;
import pl.grupa33inf.ssi.data_store.api.INodeDataStore;
import pl.grupa33inf.ssi.data_store.api.NodeValueWrite;
import pl.grupa33inf.ssi.data_store.api.NodeVariable;

@NoArgsConstructor
public class INodeDataStoreImpl implements INodeDataStore {

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public Optional<NodeVariable> readVariable(UUID deviceUUID, String variableName) throws ExecutionException, InterruptedException {
        ReadVariableTask task = new ReadVariableTask();
        return task.execute(deviceUUID.toString(), variableName).get();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Map<String, NodeVariable> readAllVariables(UUID deviceUUID) throws ExecutionException, InterruptedException {
        ReadVariablesTask task = new ReadVariablesTask();
        return task.execute(deviceUUID.toString()).get();
    }

    @Override
    public void writeVariable(UUID deviceUUID, String variableName, String value) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference variable = mDatabase.child("nodes")
                .child(deviceUUID.toString())
                .child(variableName);
        variable.child("value").setValue(value);
        variable.child("history").push().setValue(new NodeValueWrite(value, new Date().getTime()));


    }

    @Override
    public void deleteVariable(UUID deviceUUID, String variableName) {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference variable = mDatabase.child("nodes").child(deviceUUID.toString()).child(variableName);
        variable.removeValue().getResult();
    }

}
