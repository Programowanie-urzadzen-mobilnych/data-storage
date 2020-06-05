package pl.grupa33inf.ssi.data_store;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.android.gms.common.util.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import pl.grupa33inf.ssi.data_store.api.NodeVariable;

public class ReadVariablesTask extends AsyncTask<String, Void, Map<String, NodeVariable>> {
    private static final String FIREBASE_URL = "https://smart-river-23df4.firebaseio.com/";
    private static final Gson gson = new Gson();
    private static final String TAG = "VARIABLES";


    @Override
    protected Map<String, NodeVariable> doInBackground(String... strings) {
        HttpURLConnection connection = null;

        try {
            connection = getNodeListConnection(strings[0]);
            return getVariables(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new HashMap<>();
    }

    private Map<String, NodeVariable> getVariables(HttpURLConnection connection) throws IOException {
        Reader reader = new InputStreamReader(connection.getInputStream());


        BufferedReader br = new BufferedReader((reader));
        String json = br.lines().collect(Collectors.joining());

        if(Strings.isEmptyOrWhitespace(json) || json.equalsIgnoreCase("null")){
            Log.d(TAG, "getVariables: Got null input stream");
            return new HashMap<>();
        }

        Map<String, NodeVariable> map = gson.fromJson(json, Map.class);
        if(map == null){
            Log.d(TAG, "getVariables: null map");
            return new HashMap<>();
        }

        Log.d(TAG, "map size" + map.size());
        return map;
    }

    private HttpURLConnection getNodeListConnection(String deviceUUID) throws IOException {
        String address = FIREBASE_URL + "nodes/" + deviceUUID + ".json";
        Log.d(TAG, "getNodeListConnection: address: " + address);
        URL url = new URL(address);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setReadTimeout(10000);
        con.setConnectTimeout(10000);
        con.setRequestMethod("GET");
        con.setUseCaches(false);
        con.setAllowUserInteraction(false);
        con.setRequestProperty("Content-Type", "application/json");

        return con;
    }
}
