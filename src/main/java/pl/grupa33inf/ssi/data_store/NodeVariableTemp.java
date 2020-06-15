package pl.grupa33inf.ssi.data_store;

import java.util.Map;
import java.util.stream.Collectors;

import lombok.Value;
import pl.grupa33inf.ssi.data_store.api.NodeValue;
import pl.grupa33inf.ssi.data_store.api.NodeValueWrite;
import pl.grupa33inf.ssi.data_store.api.NodeVariable;

@Value
class NodeVariableTemp {
    NodeValue currentValue;
    Map<String, NodeValueWrite> history;
    boolean readonly;

    public NodeVariable toProperValue() {
        return new NodeVariable(currentValue,
                history.values()
                        .stream()
                        .map(NodeValueWrite::toProperValue)
                        .collect(Collectors.toList()),
                readonly);
    }
}
