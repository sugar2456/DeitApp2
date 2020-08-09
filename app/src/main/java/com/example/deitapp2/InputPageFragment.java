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

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Record record = new Record(weight.getText().toString(), body_fat.getText().toString(),
                        bmi.getText().toString(), metabolism.getText().toString());
                String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                myRef.child("users").child(user.getUid()).child(date).setValue(record);
//                new AsyncTask<Void, Void, Void>() {
//                    @Override
//                    public Void doInBackground(Void... params) {
//                        HttpSendJson httpSendJson = new HttpSendJson();
//                        String response = httpSendJson.callPost("http://10.0.2.2:8090/data", json.toString());
//                        System.out.println(response);
//
//
//                        return null;
//                    }
//                }.execute();
            }
        });
        return rootView;
    }
}
