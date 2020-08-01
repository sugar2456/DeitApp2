package com.example.deitapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText weight = (EditText) findViewById(R.id.input_weight);
        final EditText body_fat = (EditText) findViewById(R.id.input_body_fat);
        final EditText bmi = (EditText) findViewById(R.id.input_bmi);
        final EditText metabolism = (EditText) findViewById(R.id.input_metabolism);
        final TextView result = (TextView) findViewById(R.id.resultText);
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final JSONObject json = new JSONObject();
                try {
                    json.put("weight", weight.getText());
                    json.put("body_fat", body_fat.getText());
                    json.put("bmi", bmi.getText());
                    json.put("metabolism", metabolism.getText());
                    result.setText(json.toString());
                } catch (org.json.JSONException e) {

                }
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    public Void doInBackground(Void... params) {
                        HttpSendJson httpSendJson = new HttpSendJson();
                        String result = httpSendJson.callPost("http://10.0.2.2:8090/data", json.toString());
                        System.out.println(result);
                        return null;
                    }
                }.execute();
            }
        });
    }
}