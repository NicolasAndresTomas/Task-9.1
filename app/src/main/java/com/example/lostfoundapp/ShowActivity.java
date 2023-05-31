// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
package com.example.lostfoundapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.util.Util;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ListView view;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        databaseHelper = new DatabaseHelper(this);

        view = findViewById(R.id.listView);

        // Create a new ArrayList to store the data to be displayed in the ListView
        arrayList = new ArrayList<>();

        // Create a new ArrayAdapter to bind the ArrayList to the ListView
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        // Set the adapter for the ListView
        view.setAdapter(arrayAdapter);

        // Retrieve all adverts from the database using the getAllAdverts method of the DatabaseHelper class
        Cursor cursor = databaseHelper.getAllAdvertsCursor();

        // If there are any adverts in the database, iterate over them and add each one to the dataList ArrayList
        if (cursor.moveToFirst()) {
            do {
                // Get the data for the advertisement from the cursor
                @SuppressLint("Range") String selected_option = cursor.getString(cursor.getColumnIndex(Util.SELECTED_OPTION));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Util.NAME));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(Util.PHONE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(Util.DESCRIPTION));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(Util.DATE));
                @SuppressLint("Range") String latitude = cursor.getString(cursor.getColumnIndex(Util.LATITUDE));
                @SuppressLint("Range") String longitude = cursor.getString(cursor.getColumnIndex(Util.LONGITUDE));

                // Create a new string with all the advert data
                String data = "Selected Option: " + selected_option + "\nName: " + name + "\nPhone: " + phone + "\nDescription: " + description + "\nDate: " + date + "\nCoordinates: " + latitude + ", " + longitude;

                // Add the data to the dataList ArrayList
                arrayList.add(data);
            }
            while (cursor.moveToNext());
        }
        cursor.close();

        // Notify the adapter that the data has changed, so that it will refresh the ListView
        arrayAdapter.notifyDataSetChanged();

        // Set up item click listener for ListView
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected item from the ListView
                String selectedItem = (String) adapterView.getItemAtPosition(position);

                // Parse the selected item to get the name of the person who posted the adverts
                String name = selectedItem.substring(selectedItem.indexOf("Name: ") + 6, selectedItem.indexOf("\nPhone: "));

                // Create a new intent to open the DetailsActivity
                Intent intent = new Intent(ShowActivity.this, DetailsActivity.class);

                // Pass the selected name to the DetailsActivity using the intent's extra data
                intent.putExtra("name", name);

                // Start the DetailsActivity
                startActivity(intent);
            }
        });
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023