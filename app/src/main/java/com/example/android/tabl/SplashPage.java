package com.example.android.tabl;

/**
 * TODO: This is now the splashscreen, not the StartPage.
 * TODO: Refactor to fit SplashScreen - change to image fragment?
 */

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SplashPage extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /*if(ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION ) !=
                PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION  },
                    );
        }*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snapToCurrentLocation(view);
            }
        });
    }

    //consider implementing point focus if required
    public void snapToCurrentLocation(View view){
        //CameraUpdate currentLocation = mMap.get;
        /*Location l = mFusedLocationClient.getLastLocation();
        LatLng currentLocation = new LatLng(mFusedLocationClient.getLastLocation());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 0));
        Snackbar.make(view, "functionality not implemented",
                Snackbar.LENGTH_LONG).setAction("Action", null)
                .show();*/
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}
