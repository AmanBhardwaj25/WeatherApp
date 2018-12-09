package com.example.amanbhardwaj.nasaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private String url = "http://api.openweathermap.org/data/2.5/weather?zip=76019,us&APPID=bab1364e8cda653ad3e2f29ab44035";
    private String url = "http://api.openweathermap.org/data/2.5/weather?zip=76019,us&APPID=fec2b8a5034746f2a4641f077596b786\n";

    private Button getData;
    private ListView listData;
    private RequestQueue requestQueue;
    private ArrayList<String> mArrayAdapter;
    String[] items = new String[4];
    JSONArray ja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        getData = (Button) findViewById(R.id.getData);
        listData = (ListView) findViewById(R.id.listView);


        final JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            ja = response.getJSONArray("weather");


                            JSONObject jsonObject = ja.getJSONObject(0);


                            String id = jsonObject.getString("id");
                            String main = jsonObject.getString("main");
                            String desc = jsonObject.getString("description");
                            String icon = jsonObject.getString("icon");

                            Log.d("HERE", id);
                            Log.d("HERE", main);


                            items[0] = id;
                            items[1] = main;
                            items[2] = desc;
                            items[3] = icon;

                            //List<String> data_list = new ArrayList<>(Arrays.asList(items));
                            Log.d("HERE", "ARRAY");
                            Log.d("ID",id);
                            Log.d("MAIN",main);
                            ArrayAdapter mArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_list_item_1, items);
                            listData.setAdapter(mArrayAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");

                    }
                }
        );


        getData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                requestQueue.add(jor);
            }
        });


    }
}
