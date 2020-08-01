package com.example.deitapp2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

public class InputPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_input, container, false);

        final EditText weight = (EditText) rootView.findViewById(R.id.input_weight);
        final EditText body_fat = (EditText) rootView.findViewById(R.id.input_body_fat);
        final EditText bmi = (EditText) rootView.findViewById(R.id.input_bmi);
        final EditText metabolism = (EditText) rootView.findViewById(R.id.input_metabolism);
        final TextView result = (TextView) rootView.findViewById(R.id.resultText);
        Button sendButton = (Button) rootView.findViewById(R.id.send_button);
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
                    // TODO 適切なExceptionを作成する
                }
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    public Void doInBackground(Void... params) {
                        HttpSendJson httpSendJson = new HttpSendJson();
                        String response = httpSendJson.callPost("http://10.0.2.2:8090/data", json.toString());
                        System.out.println(response);
                        return null;
                    }
                }.execute();
            }
        });
        return rootView;
    }
}
