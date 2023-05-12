// SIT305 Mobile Application Development
//// Task: Pass Task 7.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 12-05-2023
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
    DatabaseHelper db;
    ListView listView;
    ArrayList<String> dataList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        db = new DatabaseHelper(this);

        listView = findViewById(R.id.listView);

        // Create a new ArrayList to store the data to be displayed in the ListView
        dataList = new ArrayList<>();

        // Create a new ArrayAdapter to bind the ArrayList to the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);

        // Set the adapter for the ListView
        listView.setAdapter(adapter);

        // Retrieve all adverts from the database using the getAllAdverts method of the DatabaseHelper class
        Cursor cursor = db.getAllAdverts();

        // If there are any adverts in the database, iterate over them and add each one to the dataList ArrayList
        if (cursor.moveToFirst()) {
            do {
                // Get the data for the advertisement from the cursor
                @SuppressLint("Range") String selected_option = cursor.getString(cursor.getColumnIndex(Util.SELECTED_OPTION));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(Util.NAME));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(Util.PHONE));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(Util.DESCRIPTION));
                @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(Util.DATE));
                @SuppressLint("Range") String location = cursor.getString(cursor.getColumnIndex(Util.LOCATION));

                // Create a new string with all the advert data
                String data = "Selected Option: " + selected_option + "\nName: " + name + "\nPhone: " + phone + "\nDescription: " + description + "\nDate: " + date + "\nLocation: " + location;

                // Add the data to the dataList ArrayList
                dataList.add(data);

            } while (cursor.moveToNext());
        }
        cursor.close();

        // Notify the adapter that the data has changed, so that it will refresh the ListView
        adapter.notifyDataSetChanged();

        // Set up item click listener for ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
//// Task: Pass Task 7.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 12-05-2023