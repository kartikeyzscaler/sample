package com.example.kartikeyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void onRegister(View view){
        TextView txtFirstName=findViewById(R.id.txtFirstName);
        TextView txtLastName=findViewById(R.id.txtLastName);
        TextView txtEmail=findViewById(R.id.txtEmail);


        EditText edtFirstName=findViewById(R.id.edtFirstName);
        EditText edtLastName=findViewById(R.id.edtLastName);
        EditText edtEmail=findViewById(R.id.edtEmail);

        txtFirstName.setText("First Name: "+edtFirstName.getText().toString());
        txtLastName.setText("Last Name : "+edtLastName.getText().toString());
        txtEmail.setText("Email : "+edtEmail.getText().toString());

    }


}