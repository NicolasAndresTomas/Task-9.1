// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
package com.example.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.model.Advert;

public class DetailsActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView selectedOptionText;
    TextView nameText;
    TextView phoneText;
    TextView descriptionText;
    TextView dateText;
    TextView coordinateText;
    Button deleteButton;
    Advert advert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        databaseHelper = new DatabaseHelper(DetailsActivity.this);
        selectedOptionText = findViewById(R.id.selectedOptionTextView);
        nameText = findViewById(R.id.nameTextView);
        phoneText = findViewById(R.id.phoneTextView);
        descriptionText = findViewById(R.id.descriptionTextView);
        dateText = findViewById(R.id.dateTextView);
        coordinateText = findViewById(R.id.coordinateTextView);
        deleteButton = findViewById(R.id.deleteButton);

        // Get the name of the selected user from the intent
        String name = getIntent().getStringExtra("name");

        // Get the user object from the database
        advert = databaseHelper.getAdvertByName(name);

        // Display the user details in the textviews
        selectedOptionText.setText("Selected Option: " + advert.getSelectedOption());
        nameText.setText("Name: " + advert.getName());
        phoneText.setText("Phone: " + advert.getPhone());
        descriptionText.setText("Description: " + advert.getDescription());
        dateText.setText("Date: " + advert.getDate());
        coordinateText.setText("Coordinate: " + advert.getLatitude() + ", " + advert.getLongitude());

        // Set up the delete button click listener
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                // Delete the selected user from the database
                databaseHelper.deleteAdvertByName(name);
                // Return to the previous activity
                finish();
            }
        });
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023