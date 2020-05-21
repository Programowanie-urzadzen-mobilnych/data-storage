package pl.grupa33inf.ssi.data_store;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.NoArgsConstructor;
import pl.grupa33inf.ssi.data_store.api.INodeDataStore;
import pl.grupa33inf.ssi.data_store.api.NodeVariable;
import pl.grupa33inf.ssi.data_store.api.Result;

@NoArgsConstructor
public class INodeDataStoreImpl implements INodeDataStore {

    @Override
    public NodeVariable readVariable(UUID deviceUUID, String variableName) {
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
        Log.d("INodeDataStore", String.format("%s, %s, %s", deviceUUID.toString(), variableName, value));
        return Result.OK;
    }

    @Override
    public Result deleteVariable(UUID deviceUUID, String variableName) {
        return Result.OK;
    }
}
