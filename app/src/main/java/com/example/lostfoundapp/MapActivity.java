// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
package com.example.lostfoundapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.model.Advert;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{
    private DatabaseHelper databaseHelper;
    private GoogleMap map;
    private Marker marker;
    private List<Advert> advertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initializes the DatabaseHelper and retrieve the list of items from the database
        databaseHelper = new DatabaseHelper(MapActivity.this);
        advertList = databaseHelper.getAllAdverts();

        // Retrieve the SupportMapFragment from the layout and set up the map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) MapActivity.this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        this.map = map;

        // Set up a listener for long clicks on the map to add a marker
        this.map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                if (marker != null) {
                    marker.remove();
                }

                // Set up a listener for long clicks on the map to add a marker
                MarkerOptions markerOptions = new MarkerOptions().position(latLng);
                marker = MapActivity.this.map.addMarker(markerOptions);
            }
        });

        // Set up a marker click listener
        this.map.setOnMarkerClickListener(MapActivity.this);
        this.map.getUiSettings().setZoomControlsEnabled(true);

        // Set the default location and zoom level for the map
        LatLng defaultLocation = new LatLng(-37.9715652, 144.7235037);
        this.map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12f));

        // Add markers for each item in the item list
        addMarkersForItems();
    }

    // Add markers for the items in the item list
    private void addMarkersForItems() {
        for (Advert item : advertList) {
            LatLng latLng = new LatLng(item.getLatitude(), item.getLongitude());
            map.addMarker(new MarkerOptions().position(latLng).title(item.getName()).snippet("Phone Number: " + item.getPhone() + ", Type: " + item.getSelectedOption()));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Advert clickedItem = null;
        for (Advert item : advertList) {
            LatLng latLng = new LatLng(item.getLatitude(), item.getLongitude());
            if (latLng.equals(marker.getPosition())) {
                clickedItem = item;
                break;
            }
        }

        if (clickedItem != null) {
            Toast.makeText(this, "Item Name: " + clickedItem.getName(), Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        if (marker != null) {
            // If a marker is selected, pass its position back to the calling activity
            LatLng position = marker.getPosition();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("latitude", position.latitude);
            resultIntent.putExtra("longitude", position.longitude);
            setResult(RESULT_OK, resultIntent);
        }
        else {
            setResult(RESULT_CANCELED);
        }
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database helper when the activity is destroyed
        databaseHelper.close();
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
