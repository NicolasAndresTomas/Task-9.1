// SIT305 Mobile Application Development
//// Task: Pass Task 7.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 12-05-2023
package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lostfoundapp.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
    }

    public void onClickCreate(View view) {
        // Creates an intent to start the CreateActivity activity
        Intent signupIntent = new Intent(MainActivity.this, CreateActivity.class);

        // Starts the CreateActivity activity
        startActivity(signupIntent);
    }

    // This method is called when the "Show" button is clicked
    public void onClickShow(View view) {
        // Creates an intent to start the ShowActivity activity
        Intent showIntent = new Intent(MainActivity.this, ShowActivity.class);

        // Starts the ShowActivity activity
        startActivity(showIntent);
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 7.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 12-05-2023