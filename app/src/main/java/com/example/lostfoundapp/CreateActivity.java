// SIT305 Mobile Application Development
//// Task: Pass Task 7.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 12-05-2023
package com.example.lostfoundapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.model.Advert;

public class CreateActivity extends AppCompatActivity {
    DatabaseHelper db;
    RadioGroup checkboxGroup;
    EditText nameEditText, phoneEditText, descriptionEditText, dateEditText, locationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        db = new DatabaseHelper(this);
        checkboxGroup = findViewById(R.id.checkBoxGroup);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);
        locationEditText = findViewById(R.id.locationEditText);
    }

    public void onClickSave(View view) {
        int selectedId = checkboxGroup.getCheckedRadioButtonId();
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String location = locationEditText.getText().toString();

        // Validate input
        if (selectedId == -1 || name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
            Toast.makeText(CreateActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the selected radio button and its value
        String selectedOption = "";
        RadioButton selectedRadioButton = findViewById(selectedId);
        selectedOption = selectedRadioButton.getText().toString();

        // Insert new advert object into database
        long result = db.insertAdvert(new Advert(selectedOption,name, phone, description, date, location));

        // Show appropriate message based on result of insertion
        if (result > 0) {
            Toast.makeText(CreateActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CreateActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
        }

        // Close activity
        finish();
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 7.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 12-05-2023