package pl.grupa33inf.ssi.data_store;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.common.util.Strings;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

import pl.grupa33inf.ssi.data_store.api.NodeVariable;

public class ReadVariableTask extends AsyncTask<String, Void, Optional<NodeVariable>> {
    private static final String FIREBASE_URL = "https://smart-river-23df4.firebaseio.com/";
    private static final Gson gson = new Gson();
    private static final String TAG = "VARIABLES";

    @Override
    protected Optional<NodeVariable> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        try {
            connection = getNodeConnection(strings[0], strings[1]);
            return getVariable(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        return Optional.empty();
    }


    private Optional<NodeVariable> getVariable(HttpURLConnection connection) throws IOException {
        Reader reader = new InputStreamReader(connection.getInputStream());

        BufferedReader br = new BufferedReader((reader));
        String json = br.lines().collect(Collectors.joining());

        if (Strings.isEmptyOrWhitespace(json) || json.equalsIgnoreCase("null")) {
            Log.d(TAG, "getVariables: Got null input stream");
            return Optional.empty();
        }

        NodeVariableTemp obj = gson.fromJson(reader, NodeVariableTemp.class);

        return Optional.ofNullable(obj).map(NodeVariableTemp::toProperValue);
    }


    private HttpURLConnection getNodeConnection(String deviceUUID, String variableName) throws IOException {
        URL url = new URL(FIREBASE_URL + "nodes/" + deviceUUID + "/" + variableName + ".json");
        return (HttpURLConnection) url.openConnection();
    }
}
