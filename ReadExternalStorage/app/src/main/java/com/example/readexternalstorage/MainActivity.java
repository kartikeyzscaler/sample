package com.example.readexternalstorage;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    Button read;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = findViewById(R.id.output);
        read = findViewById(R.id.read);
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String state = Environment.getExternalStorageState();
                System.out.println("here 1");
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    System.out.println("here 2");

                    if (Build.VERSION.SDK_INT >= 23) {
                        System.out.println("here 3");

                        if (checkPermission()) {

//                            File sdcard = Environment.getExternalStorageDirectory();
                            File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//                            File dir = new File(sdcard.getAbsolutePath() + "/text/");
                            System.out.println("File path is "+dir);

                            if(dir.exists()) {
                                System.out.println("DIR exists");

                                File file = new File(dir, "Test.txt");
                                System.out.println("File exists "+file);

                                FileOutputStream os = null;
                                StringBuilder text = new StringBuilder();
                                try {
                                    System.out.println("Try is working");
                                    BufferedReader br = new BufferedReader(new FileReader(file));
                                    String line;
                                    while ((line = br.readLine()) != null) {
                                        text.append(line);
                                        text.append(' ');
                                        System.out.println("While is working");

                                    }
                                    System.out.println("Text is "+text);

                                    br.close();
                                } catch (IOException e) {
                                    System.out.println("error occured "+ e.getMessage());
                                    //You'll need to add proper error handling here
                                }
                                System.out.println(text);

                                output.setText(text);
                            }
                        } else {
                            requestPermission(); // Code for permission
                        }
                    } else {
//                        File sdcard = Environment.getExternalStorageDirectory();
//                        File dir = new File(sdcard.getAbsolutePath() + "/text/");
                        System.out.println("here 4");

                        File dir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        if(dir.exists()) {
                            File file = new File(dir, "Test.txt");
                            FileOutputStream os = null;
                            StringBuilder text = new StringBuilder();
                            try {
                                BufferedReader br = new BufferedReader(new FileReader(file));
                                String line;
                                while ((line = br.readLine()) != null) {
                                    text.append(line);
                                    text.append(' ');
                                }
                                br.close();
                            } catch (IOException e) {
                                //You'll need to add proper error handling here
                            }
                            System.out.println(text);

                            output.setText(text);
                        }
                    }
                }
            }
        });
    }

    private boolean checkPermission() {
        System.out.println("Check permission");

        int result = ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            System.out.println("have Permission");

            return true;
        } else {
            System.out.println("Do not have Permission");

            return false;
        }
    }
    private void requestPermission() {
        System.out.println("Requested Permission");

        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(MainActivity.this, "Write External Storage permission allows us to read files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.e("value", "Permission Granted, Now you can use local drive .");
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            }
            break;
        }
    }
}