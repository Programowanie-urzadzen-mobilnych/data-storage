package pl.grupa33inf.ssi.data_store;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

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

        if (checkNullResult(reader)) {
            return Optional.empty();
        }

        BufferedReader br = new BufferedReader((reader));
        br.lines().forEachOrdered(line -> Log.d(TAG, "Got json: "+ line));
        br.close();

        NodeVariable obj = gson.fromJson(reader, NodeVariable.class);

        return Optional.of(obj);
    }

    private boolean checkNullResult(Reader reader) throws IOException {
        char[] chars = new char[4];

        reader.read(chars, 0 ,4);

        if (new String(chars).equalsIgnoreCase("null")) {
            return true;
        }

        return false;
    }

    private HttpURLConnection getNodeConnection(String deviceUUID, String variableName) throws IOException {
        URL url = new URL(FIREBASE_URL + "nodes/" + deviceUUID + "/" + variableName + ".json");
        return (HttpURLConnection) url.openConnection();
    }
}
