package com.example.qvid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class NearbySalons extends AppCompatActivity  implements OnMapReadyCallback {

    private static final String TAG = "NearbyServicesActivity";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private Button btnFind, btnEnable;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_salons);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnFind = findViewById(R.id.btnFind);
        btnEnable = findViewById(R.id.btnEnable);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        btnEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLocationSettings();
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
                locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        mMap.addMarker(new MarkerOptions()
                        .title("Naturals")
                        .snippet("India's No.1 hair and beauty salon")
                        .position(new LatLng(13.117735, 80.154229)));

                        mMap.addMarker(new MarkerOptions()
                                .title("Green Trends")
                                .snippet("Unisex Hair & Style Salon")
                                .position(new LatLng(13.123168, 80.149983)));

                        mMap.addMarker(new MarkerOptions()
                                .title("Toni & Guy")
                                .snippet("You're beautiful")
                                .position(new LatLng(13.122068, 80.147418)));
//                        if(coordinates.size() != 0) {
////                            int flag = 0;
////                            for(Stop stop: coordinates) {
////                                float[] results = new float[1];
////                                Location.distanceBetween(location.getLatitude(), location.getLongitude(),
////                                        stop.getStop_latitude(), stop.getStop_longitude(), results);
////                                if(results[0]<1000) {
////                                    flag = 1;
////                                    mMap.addMarker(new MarkerOptions()
////                                            .title(stop.getStop_name())
////                                            .snippet("Click to view more details.")
////                                            .position(new LatLng(stop.getStop_latitude(), stop.getStop_longitude())));
////                                }
////                            }
////                            if(flag==0) {
////                                Toast.makeText(NearbyServicesActivity.this, "No stops nearby", Toast.LENGTH_SHORT).show();
////                            }
////                        }
                    }
                });
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        checkLocationSettings();

    }
    private void checkLocationSettings() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                //Settings of device are satisfied
                enableUserLocation();
                Toast.makeText(NearbySalons.this, "Fetching location...", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        zoomToUserLocation();
                        btnEnable.setVisibility(View.GONE);
                        btnFind.setVisibility(View.VISIBLE);
                    }
                }, 3000);
            }
        });
        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(NearbySalons.this, 1001);
                        btnEnable.setVisibility(View.VISIBLE);
                    } catch (IntentSender.SendIntentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
    @SuppressLint("MissingPermission")
    private void enableUserLocation() {
        mMap.setMyLocationEnabled(true);
    }

    private void zoomToUserLocation() {
        @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
            }
        });
    }

}