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
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android.tabl.utils.RecyclerItemClickListener;
import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.example.android.tabl.restaurant_recyclerview.RestaurantsAdapter;
import com.example.android.tabl.utils.TablUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * First main screen of TABL. Allows user to select the restaurant they intend to order from using
 * either a map or search function.
 *
 * @WRFitch
 */

/**
 * TODO: implement additional search method in appbar.
 * TODO: Implement restaurant get radius
 * TODO: update comments
 * TODO: organise variables
 */

public class FindRestaurantActivity extends AppCompatActivity
        implements OnMapReadyCallback, LocationListener, SwipeRefreshLayout.OnRefreshListener {

    //activity variables
    private List<Restaurant> restaurantList = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private RestaurantsAdapter rAdapter;
    private FloatingActionButton fab;
    private String restaurantId;
    private static AlertDialog alertDialog;

    //map variables
    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    //instantiating userLoc keeps the app from crashing without a previous location
    private static Location userLoc = new Location("dummylocation");
    private final static String KEY_LOCATION = "location";
    private final float DEFAULT_ZOOM = 16f;
    private boolean gotLocPerms = false;
    private boolean locDialogOpen = false;
    private double mapRadius = 100; //this is in DEGREES, NOT KM

    //firebase variables
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_restaurant);
        //set up map things
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        db = FirebaseFirestore.getInstance();

        if (!TablUtils.isNetworkAvailable(this))
            Toast.makeText(this, R.string.connection_failure, Toast.LENGTH_SHORT).show();
        if (savedInstanceState != null && savedInstanceState.keySet().contains(KEY_LOCATION)) {
            userLoc = savedInstanceState.getParcelable(KEY_LOCATION);
        }

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                loadMap(map);
            }
        });

        //set up UI things
        recyclerView = findViewById(R.id.find_restaurant_recyView);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getBaseContext(), MenuActivity.class);
                                intent.putExtra("restaurantName",
                                        restaurantList.get(position).getName());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                /*
                                Possible Uses:
                                - add to favourites/list
                                - get directions
                                - dialog menu with all these options
                                 */
                            }
                        })
        );

        rAdapter = new RestaurantsAdapter(restaurantList);
        RecyclerView.LayoutManager rLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(rLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(rAdapter);
        updateRestaurantData();

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        fab = findViewById(R.id.snapToLocationButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCameraWithAnimation();
            }
        });

        // message dialog
        alertDialog = new AlertDialog.Builder(FindRestaurantActivity.this).create();
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
        checkGPSTurnedOn();
        TablUtils.getLocationPerms(this, this);
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onPause() {
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        loadMap(googleMap);
    }

    @SuppressLint("MissingPermission")
    protected void loadMap(GoogleMap googleMap) {
        //TablUtils.getLocationPerms(this, this);
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        updateLocation();
        userLoc = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (userLoc != null)
            updateCameraNoAnimation(userLoc);

    }

    @SuppressLint("MissingPermission")
    public void updateLocation() {
        if (!gotLocPerms && !locDialogOpen) {
            TablUtils.getLocationPerms(this, this);
            checkGPSTurnedOn();
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 100, this);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 20, this);
        userLoc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @SuppressLint("MissingPermission")
    public void getUserLocationNoAnimation() {
        updateLocation();
        userLoc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        userLoc = mMap.getMyLocation();
        updateCameraNoAnimation(userLoc);
    }

    public void updateCameraNoAnimation(Location currentLocation) {
        LatLng currentLatLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, DEFAULT_ZOOM));
    }

    @SuppressLint("MissingPermission")
    public void updateCameraWithAnimation() {
        updateLocation();
        userLoc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        userLoc = mMap.getMyLocation();
        LatLng currentLatLng = new LatLng(userLoc.getLatitude(), userLoc.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, DEFAULT_ZOOM));
    }

    public void onLocationChanged(Location location) {
        updateLocation();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        updateLocation();
    }

    public void onProviderEnabled(String provider) {
        //TablUtils.getLocationPerms(this, this);
        updateLocation();
        updateCameraNoAnimation(userLoc);
    }

    public void onProviderDisabled(String provider) {
        TablUtils.getLocationPerms(this, this);
    }

    private void checkGPSTurnedOn() {
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locDialogOpen = true;
            AlertDialog.Builder builder = new AlertDialog.Builder(FindRestaurantActivity.this);
            builder.setCancelable(false);
            builder.setMessage(R.string.gps_dialog_info_text)
                    .setTitle(R.string.gps_dialog_title);
            builder.setPositiveButton(R.string.turn_on_gps, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    requestTurnOnGPS();
                    getParent().recreate();
                    gotLocPerms = true;
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

    private void requestTurnOnGPS() {
        Intent gpsOptionsIntent = new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);
    }

    private void showRestaurantsOnMap() {
        for (Restaurant r : restaurantList) {
            mMap.addMarker(new MarkerOptions()
                    .position(r.getLatLngLocation())
                    .title(r.getName()));
        }
    }

    private void updateRestaurantData() {
        if (!TablUtils.isNetworkAvailable(this)) {
            TablUtils.errorMsg(fab, "No Internet Connection!");
            return;
        }
        getRestaurantsInRadius();
    }

    //Abandon all hope ye who enter here
    private void getRestaurantsInRadius() {
        db = FirebaseFirestore.getInstance();
        final CollectionReference restaurantsRef = db.collection("Restaurants");
        //query whether or not each restaurant is in range of user
        Query lonQuery = restaurantsRef
                .whereLessThanOrEqualTo("Longitude", userLoc.getLongitude() + mapRadius)
                .whereGreaterThanOrEqualTo("Longitude", userLoc.getLongitude() - mapRadius);
        Query latQuery = restaurantsRef
                .whereLessThanOrEqualTo("Latitude", userLoc.getLatitude() + mapRadius)
                .whereGreaterThanOrEqualTo("Latitude", userLoc.getLatitude() - mapRadius);
        Task lonTask = lonQuery.get();
        Task latTask = latQuery.get();
        Tasks.whenAllComplete(lonTask, latTask).addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
            @Override
            public void onComplete(@NonNull Task<List<Task<?>>> task) {
                if (task.isSuccessful()) {
                    restaurantList.clear();
                    for (Task t : task.getResult()) {
                        docLoop:
                        for (QueryDocumentSnapshot document : (QuerySnapshot) t.getResult()) {
                            for (Restaurant r : restaurantList) {
                                if(document.getData().get("Name") == null) break;
                                if (document.getData().get("Name").equals(r.getName()))
                                    continue docLoop; //we can go deeper
                            }
                            restaurantList.add(new Restaurant(document.getData(), userLoc, document.getId()));
                        }
                    }
                    rAdapter.notifyDataSetChanged();
                    showRestaurantsOnMap();
                } else {
                    TablUtils.errorMsg(fab, "Data not received from Firebase");
                }
            }
        });
    }

    private void callSearchRestaurantActivity(Context c) {
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

    @Override
    public void onRefresh() {
        updateRestaurantData();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    // used for UNIT TEST -> UserSelectsValidRestaurantLocationTest
    // check users selection is within reasonable range (1 mile)
    public static Boolean isValidUserRestaurantSelection(double latitude, double longitude) {
        // test here ***
        // test user selection location is within 1 mile of their actual location
        latitude = 0;
        longitude = 0;

        if (latitude < 1 && longitude < 1) {
            return true;
        } else {
            // tell the user they have selected an invalid restaurant location
            alertDialog.setTitle("Location");
            alertDialog.setMessage("You have selected a location that is too far away");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            return false;
        }
    }


}
