package com.example.uibasics;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtMessage;
    private EditText edtTxtName;

    
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.btnCheck){
            Toast.makeText(this, "Check Button Pressed", Toast.LENGTH_SHORT).show();
            txtMessage.setText(edtTxtName.getText().toString());
        }
        else if(v.getId()==R.id.edtTxtName){
            Toast.makeText(this, "Edit Text Pressed", Toast.LENGTH_SHORT).show();

        }
    }
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnHello=findViewById(R.id.btnCheck);
        btnHello.setOnClickListener(this);

        edtTxtName=findViewById(R.id.edtTxtName);
        edtTxtName.setOnClickListener(this);
        txtMessage=findViewById(R.id.txtMessage);
    }

}