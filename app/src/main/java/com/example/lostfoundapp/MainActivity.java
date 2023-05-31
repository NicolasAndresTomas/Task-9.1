// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
package com.example.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lostfoundapp.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
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

    // This method is called when the "Show Map" button is clicked
    public void onClickShowMap(View view) {
        // Creates an intent to start the MapActivity activity
        Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);

        // Starts the MapActivity activity
        startActivity(mapIntent);
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023