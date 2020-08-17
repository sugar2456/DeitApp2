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
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

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
        Button showRecordButton = (Button) rootView.findViewById(R.id.record_button);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean retFlag = false;
                if (weight.getText().toString().isEmpty()) {
                    weight.setError("この入力項目は必須です。");
                    retFlag = true;
                }
                if (body_fat.getText().toString().isEmpty()) {
                    body_fat.setError("この入力項目は必須です。");
                    retFlag = true;
                }
                if (bmi.getText().toString().isEmpty()) {
                    bmi.setError("この入力項目は必須です。");
                    retFlag = true;
                }
                if (metabolism.getText().toString().isEmpty()) {
                    metabolism.setError("この入力項目は必須です。");
                    retFlag = true;
                }
                if (retFlag) return;

                Record record = new Record(weight.getText().toString(), body_fat.getText().toString(),
                        bmi.getText().toString(), metabolism.getText().toString());
                String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                myRef.child("users").child(user.getUid()).child(date).setValue(record);
            }
        });
        showRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = InputPageFragmentDirections.actionInputPageFragmentToSwipeFragment();
                Navigation.findNavController(v).navigate(action);
            }
        });
        return rootView;
    }
}
