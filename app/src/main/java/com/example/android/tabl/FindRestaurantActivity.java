package com.example.android.tabl;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.android.tabl.utils.RecyclerItemClickListener;
import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.example.android.tabl.restaurant_recyclerview.RestaurantsAdapter;
import com.example.android.tabl.utils.TablUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * First main screen of TABL. Allows user to select the restaurant they intend to order from using
 * either a map or search function.
 *
 * @WRFitch
 */

/**
 * TODO: implement status bar or toolbar or whatever it's called
 * TODO: implement additional search method in appbar.
 * TODO: implement passing restaurant data to MenuActivity
 * TODO: implement swiping restaurantList up & down
 * TODO: implement onPause method to stop the GPS draining someone's battery
 * TODO: implement onDestroy() and other important map methods
 */

public class FindRestaurantActivity extends AppCompatActivity
        implements OnMapReadyCallback, LocationListener{

    private List<Restaurant> restaurantList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RestaurantsAdapter rAdapter;
    private FloatingActionButton fab;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    //instantiating currentLocation keeps the app from crashing without a previous location
    private Location currentLocation = new Location("dummylocation");
    private final static String KEY_LOCATION = "location";
    private final float DEFAULT_ZOOM = 16f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_restaurant);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        checkGPSTurnedOn();

        if (savedInstanceState != null && savedInstanceState.keySet().contains(KEY_LOCATION)) {
            currentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                loadMap(map);
            }
        });


        fab = findViewById(R.id.snapToLocationButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCameraWithAnimation();
            }
        });

        recyclerView = findViewById(R.id.find_restaurant_recyView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                restaurantList.get(position).getMenuTitles();
                                //pass menuTitles to menuactivity
                                //preload favourites menu
                                callMenuActivity(getApplicationContext());
                            }
                            @Override
                            public void onLongItemClick(View view, int position) {
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

        showRestaurantsOnMap();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.find_restaurant_activity_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.fra_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    //check if the user has location services on when returning to the application
    @Override
    @SuppressLint("MissingPermission")
    protected void onStart() {
        super.onStart();
        TablUtils.getLocationPerms(this, this);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
        //mLocationManager.removeUpdates();
        mMap.setMyLocationEnabled(false);
        super.onPause();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                loadMap(map);
            }
        });
        super.onResume();
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        loadMap(googleMap);
    }

    @SuppressLint("MissingPermission")
    protected void loadMap(GoogleMap googleMap) {
        TablUtils.getLocationPerms(this, this);
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        updateLocation();
        currentLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(currentLocation!=null)
            updateCameraNoAnimation(currentLocation);

    }

    @SuppressLint("MissingPermission")
    public void updateLocation() {
        TablUtils.getLocationPerms(this, this);
        checkGPSTurnedOn();
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 100, this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 20, this);
    }

    @SuppressLint("MissingPermission")
    public void getUserLocationNoAnimation() {
        updateLocation();
        currentLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        currentLocation = mMap.getMyLocation();
        updateCameraNoAnimation(currentLocation);
    }

    public void updateCameraNoAnimation(Location currentLocation) {
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, DEFAULT_ZOOM));
    }

    @SuppressLint("MissingPermission")
    public void updateCameraWithAnimation() {
        updateLocation();
        currentLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        currentLocation = mMap.getMyLocation();
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, DEFAULT_ZOOM));
    }

    public void onLocationChanged(Location location) {
        updateLocation();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        updateLocation();
    }

    public void onProviderEnabled(String provider) {
        TablUtils.getLocationPerms(this, this);
        updateLocation();
        updateCameraNoAnimation(currentLocation);
    }

    public void onProviderDisabled(String provider) {
        TablUtils.getLocationPerms(this, this);
    }

    private void checkGPSTurnedOn(){
        if( !mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            !mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            AlertDialog.Builder builder = new AlertDialog.Builder(FindRestaurantActivity.this);

            builder.setMessage(R.string.gps_dialog_info_text)
                    .setTitle(R.string.gps_dialog_title);
            builder.setPositiveButton(R.string.turn_on_gps, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    requestTurnOnGPS();
                    getParent().recreate();
                }
            });
            builder.setNegativeButton(R.string.use_search_not_gps, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    callSearchRestaurantActivity(getApplicationContext());
                    //callSearchRestaurantActivity(FindRestaurantActivity.this);
                }
            });
            builder.show();
            AlertDialog dialog = builder.create();
        }
    }

    private void requestTurnOnGPS(){
        Intent gpsOptionsIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }

    private void showRestaurantsOnMap() {
        for (Restaurant r : restaurantList) {
            //currently crashes app - "null object reference"
            //mMap.addMarker(new MarkerOptions().position(r.updateLocation()).title(r.getName()));
        }
    }

    private void prepRestaurantData() {
        //current implementation uses test data! something like i->getCachedRestaurants
        for (int i = 0; i < 5; i++) {
            restaurantList.add(new Restaurant(FindRestaurantActivity.this));
        }
        rAdapter.notifyDataSetChanged();
    }

    //call next activity. Make sure to pass parcelable restaurant data.
    private void callMenuActivity(Context c) {
        Intent intent = new Intent(c, MenuActivity.class);
        startActivity(intent);
    }

    private void callSearchRestaurantActivity(Context c){
        Intent intent = new Intent(c, SearchRestaurantActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.fra_search:
                return true;

            default:
                //TablUtils.errorMsg(fab, "action not recognised!");
                return super.onOptionsItemSelected(item);

        }
    }
}
