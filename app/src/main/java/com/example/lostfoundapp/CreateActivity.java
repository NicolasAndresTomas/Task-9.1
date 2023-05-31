// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023
package com.example.lostfoundapp;
import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.lostfoundapp.data.DatabaseHelper;
import com.example.lostfoundapp.model.Advert;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CreateActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    RadioGroup checkboxGroup;
    EditText nameEditText;
    EditText phoneEditText;
    EditText descriptionEditText;
    EditText dateEditText;
    Double cLatitude;
    Double cLongitude;
    FusedLocationProviderClient fusedLocationProviderClient;
    AutocompleteSupportFragment autoCompleteFragment;
    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        databaseHelper = new DatabaseHelper(this);
        checkboxGroup = findViewById(R.id.checkBoxGroup);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        dateEditText = findViewById(R.id.dateEditText);

        // Set up the FusedLocationProviderClient for retrieving the user's last known location
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Initialize the Google API from (res > values > strings.xml)
        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));

        // Retrieves the AutocompleteSupportFragment from the layout
        autoCompleteFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.apiLocationFragment);

        // Sets the fields to be retrieved from the selected place
        autoCompleteFragment.setPlaceFields(Arrays.asList(Place.Field.NAME, Place.Field.LAT_LNG));

        // Set an OnPlaceSelectedListener to handle place selection
        autoCompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // Retrieve the latitude and longitude values from the selected place
                LatLng latLng = place.getLatLng();
                cLatitude = latLng.latitude;
                cLongitude = latLng.longitude;

                EditText apiLocationEditText = findViewById(R.id.apiLocationEditText);
                apiLocationEditText.setText(place.getName());
            }

            @Override
            public void onError(@NonNull Status status) {
                // Handle any errors that occur during place selection
                Log.e(TAG, "An error occurred: " + status);
            }
        });
    }

    public void onClickCurrentLocation(View view) {
        // Check if the location permission has been granted
        if (ContextCompat.checkSelfPermission(CreateActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreateActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
        else {
                fusedLocationProviderClient.getLastLocation().addOnSuccessListener(CreateActivity.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        cLatitude = location.getLatitude();
                        cLongitude = location.getLongitude();

                        // Use a Geocoder to get the address for the location
                        Geocoder geocoder = new Geocoder(CreateActivity.this, Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(cLatitude, cLongitude, 1);
                            if (!addresses.isEmpty()) {
                                // Set the obtained address as the text of the AutocompleteSupportFragment
                                String address = addresses.get(0).getAddressLine(0);
                                // Update the EditText with the selected location
                                EditText selectedLocationEditText = findViewById(R.id.apiLocationFragment);
                                selectedLocationEditText.setText(address);

                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void onClickSave(View view) {
        int selectedId = checkboxGroup.getCheckedRadioButtonId();
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String date = dateEditText.getText().toString();

        // Validate input
        if (selectedId == -1 || name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty()) {
            Toast.makeText(CreateActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the selected radio button and its value
        String selectedOption = "";
        RadioButton selectedRadioButton = findViewById(selectedId);
        selectedOption = selectedRadioButton.getText().toString();

        // Insert new advert object into database
        long result = databaseHelper.insertAdvert(new Advert(selectedOption,name, phone, description, date, cLatitude, cLongitude));

        // Show appropriate message based on result of insertion
        if (result > 0) {
            Toast.makeText(CreateActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(CreateActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
        }

        // Close activity
        finish();
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 9.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 30-05-2023