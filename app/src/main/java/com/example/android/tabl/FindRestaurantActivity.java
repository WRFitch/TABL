package com.example.android.tabl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.tabl.location_utils.GetCompleteAddress;
import com.example.android.tabl.utils.RecyclerItemClickListener;
import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.example.android.tabl.restaurant_recyclerview.RestaurantsAdapter;
import com.example.android.tabl.utils.TablUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
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
 * TODO: implement snapToLocation() on floatingActionButton
 * TODO: implement map utilities.
 * TODO: implement additional search method.
 * TODO: implement passing restaurant data to MenuActivity
 * TODO: clean up this class - currently setting up for dependency hell
 */

public class FindRestaurantActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private List<Restaurant> restaurantList= new ArrayList<>();
    private RecyclerView recyclerView;
    private RestaurantsAdapter rAdapter;
    private boolean dialogIsShowing = false;
    private Restaurant selectedRestaurant;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private Location mCurrentLocation;
    private final static String KEY_LOCATION = "location";
    private final float DEFAULT_ZOOM = 16f;

    /*
     * Define a request code to send to Google Play services This code is
     * returned in Activity.onActivityResult
     */
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_restaurant);

        if(TextUtils.isEmpty(getResources().getString(R.string.google_maps_key))){
            throw new IllegalStateException("no gmaps api key!");
        }

        if (savedInstanceState != null && savedInstanceState.keySet().contains(KEY_LOCATION)) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap map) {
                    loadMap(map);
                }
            });
        } else {
            TablUtils.errorMsg(recyclerView, "Error - map fragment was null");
        }

        FloatingActionButton fab = findViewById(R.id.snapToLocationButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snapToCurrentLocation(view);
            }
        });

        recyclerView = findViewById(R.id.find_restaurant_recyView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        restaurantList.get(position).getMenuTitles();
                        //pass menuTitles to menuactivity
                        //preload favourites menu
                        callMenuActivity(view.getContext());
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        //perhaps use this to display restaurant info/add to favourites?
                        TablUtils.functionNotImplemented(view, "maybe add to favourites?");
                    }
                })
        );

        rAdapter = new RestaurantsAdapter(restaurantList);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(rLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        prepRestaurantData();
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

    //check if the user has location services on when returning to the application
    @Override
    protected void onStart() {
        super.onStart();
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        buildAlertMessageNoGps();
    }

    protected void loadMap(GoogleMap googleMap){
        mMap = googleMap;
        if (mMap != null) {

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //set up map object & perms
        mMap = googleMap;
        if (!checkLocationPermission()) {
            getLocation();
        }

        //set up map camera based on user location
        LatLng userLocation = new LatLng(-34, 151);

        //get nearby restaurants and mark on map
        mMap.addMarker(new MarkerOptions().position(userLocation).title("Marker in Sydney"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation));

    }

    @SuppressLint("MissingPermission")
    public void getLocation() {
        // get location using both network and gps providers, no need for permission check as that is done before the method is called
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 1000, this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1000, this);
    }

    //returns true if we have location permission
    private Boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        } else {
            return false;
        }
        return true;
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
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(getUserLocation()));
    }



    //call next activity. Make sure to pass parcelable restaurant data.
    private void callMenuActivity(Context c) {
        Intent intent = new Intent(c, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    //from here on out there is only george code.

    //alert dialog to get gps coordinates
    private void buildAlertMessageNoGps() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        final AlertDialog alertDialog =  builder1.create();
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.gps_confirmation_dialog, null);

        alertDialog.setTitle("GPS permissions needed");
        alertDialog.setCancelable(false);

        final TextView confirmGpsMessage = (TextView) promptView.findViewById(R.id.gps_confirm_txt);
        confirmGpsMessage.requestFocus();
        confirmGpsMessage.setMovementMethod(LinkMovementMethod.getInstance());
        Button confirmGpsButton = (Button)promptView.findViewById(R.id.confirm_gps_btn);
        Button denyGpsButton = (Button) promptView.findViewById(R.id.deny_gps_btn);

        confirmGpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                dialogIsShowing = false;
                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }
        });
        denyGpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                dialogIsShowing = false;
                finish();
            }
        });

        //prevent multiple alert boxes stacking on top of each other
        if(dialogIsShowing)
            return;
        //only show the dialog if gps is not enabled
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            alertDialog.setView(promptView);
            alertDialog.show();
            dialogIsShowing = true;
        }

    }

    public void updateMarker(Location currentLocation) {
        LatLng currentLatlng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatlng, DEFAULT_ZOOM));
        List<Address> currentAddress = new GetCompleteAddress(currentLocation, this).getAddress();

        Marker currentLocationInfo = mMap.addMarker(new MarkerOptions()
                .position(currentLatlng)
                .title("Your Location:")
                .snippet(currentAddress.get(0).getAddressLine(0)));
        //mMap.setInfoWindowAdapter(new CustomInfoAdapter(FindRestaurantActivity.this));//what is this for?
        //need map marker
        currentLocationInfo.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.black_close_exit_button));
        currentLocationInfo.showInfoWindow();
    }

    public void onLocationChanged(Location location) {
        updateMarker(location);
    }

    //required to extend LocationListener
    public void onStatusChanged(String provider, int status, Bundle extras) { }

    public void onProviderEnabled(String provider) {
        buildAlertMessageNoGps();
        getLocation();
    }

    public void onProviderDisabled(String provider) {
        buildAlertMessageNoGps();
    }
}
