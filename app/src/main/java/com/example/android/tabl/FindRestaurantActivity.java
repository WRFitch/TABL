package com.example.android.tabl;

import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.example.android.tabl.restaurant_recyclerview.RestaurantsAdapter;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * First main screen of TABL. Allows user to select the restaurant they intend to order from using
 * either a map or search function.
 *
 * @WRFitch
 */

/**
 * TODO: get location permissions from user
 * TODO: implement snapToLocation() on floatingActionButton
 * TODO: implement list of restaurants, including UI and populate() method.
 * TODO: implement list highlighting & map utilities.
 * TODO: implement additional search method.
 */

public class FindRestaurantActivity extends AppCompatActivity implements OnMapReadyCallback {

    private List<Restaurant> restaurantList= new ArrayList<>();
    private RecyclerView recyclerView;
    private RestaurantsAdapter rAdapter;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_restaurant);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        /*
        if(ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION ) !=
                PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[] {
                            android.Manifest.permission.ACCESS_FINE_LOCATION},
                    );
        }*/

        FloatingActionButton fab = findViewById(R.id.snapToLocationButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snapToCurrentLocation(view);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.find_restaurant_recyView);

        rAdapter = new RestaurantsAdapter(restaurantList);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(rLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        prepRestaurantData();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //THIS IS IN SYDNEY
        LatLng userLocation = new LatLng(-34, 151);

        //get nearby restaurants and mark on map
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker in Sydney"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));
    }

    private void snapToCurrentLocation(){
        //get user location from phone
        mMap.moveCamera(CameraUpdateFactory.newLatLng(getUserLocation()));
    }

    private LatLng getUserLocation(){
        return null;
    }

    //This method is bad and I should be ashamed.
    //consider implementing point focus if required
    private void snapToCurrentLocation(View view){
        //CameraUpdate currentLocation = mMap.get;

        /*
        Location l = mFusedLocationClient.getLastLocation();
        LatLng currentLocation = new LatLng(mFusedLocationClient.getLastLocation());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 0));
        */

        TablUtils.functionNotImplemented(view);
    }

    private void prepRestaurantData(){
        //current implementation uses test data!
        Restaurant resta;
        for(int i=0; i<5; i++){
            resta = new Restaurant(FindRestaurantActivity.this);
            restaurantList.add(resta);
        }
        rAdapter.notifyDataSetChanged();
    }
}
