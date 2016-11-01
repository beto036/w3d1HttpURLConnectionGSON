package com.example.admin.w3d1gsonexample;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.admin.w3d1gsonexample.model.Result;
import com.example.admin.w3d1gsonexample.model.Result_;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTAG_";
    OkHttpClient client;
    private String json = "[{\"results\":[{\"gender\":\"female\",\"name\":{\"title\":\"miss\",\"first\":\"inmaculada\",\"last\":\"gil\"},\"location\":{\"street\":\"7637 calle de alberto aguilera\",\"city\":\"zaragoza\",\"state\":\"andalucía\",\"postcode\":72479},\"email\":\"inmaculada.gil@example.com\",\"login\":{\"username\":\"orangetiger521\",\"password\":\"sergeant\",\"salt\":\"B1d1y4lk\",\"md5\":\"78d17bd004369f9b5b6090187a289f00\",\"sha1\":\"43d01d2d2b1ce38f9869935087a5a81ac06ab820\",\"sha256\":\"1501ce26ea391452b6a70b930e2154ecd5fccfd5988d1681153622e04b8e3985\"},\"dob\":\"1957-05-14 21:26:30\",\"registered\":\"2009-09-14 03:41:42\",\"phone\":\"907-473-902\",\"cell\":\"619-066-850\",\"id\":{\"name\":\"DNI\",\"value\":\"65248880-Y\"},\"picture\":{\"large\":\"https://randomuser.me/api/portraits/women/77.jpg\",\"medium\":\"https://randomuser.me/api/portraits/med/women/77.jpg\",\"thumbnail\":\"https://randomuser.me/api/portraits/thumb/women/77.jpg\"},\"nat\":\"ES\"}],\"info\":{\"seed\":\"142420bf19902324\",\"results\":1,\"page\":1,\"version\":\"1.1\"}}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        client = new OkHttpClient();

        try {
//            json = run("https://randomuser.me/api");

//            URL aURL = new URL("https://randomuser.me/api");
//            URLConnection conn = aURL.openConnection();
//            conn.connect();
//            InputStream is = conn.getInputStream();

            URL url = new URL("https://randomuser.me/api");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();


            json = this.getJson(is);


            Gson gson = new GsonBuilder().create();
//            Type listType = new TypeToken<List<Result>>(){}.getType();
//            ArrayList<Result> result = gson.fromJson(json, listType);

            Result result = gson.fromJson(json, Result.class);
//
//
//
            for (Result_ result_ : result.getResults()){
                Log.d(TAG, "onCreate: " + result_.toString());
            }

            Log.d(TAG, "onCreate: " + result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private String getJson(InputStream is) throws IOException {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(is, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
}
