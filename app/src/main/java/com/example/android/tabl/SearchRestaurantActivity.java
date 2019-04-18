package com.example.android.tabl;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.tabl.restaurant_recyclerview.Restaurant;
import com.example.android.tabl.restaurant_recyclerview.RestaurantsAdapter;
import com.example.android.tabl.utils.RecyclerItemClickListener;
import com.example.android.tabl.utils.TablUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchRestaurantActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private RestaurantsAdapter rAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);
        handleIntent(getIntent());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        recyclerView = findViewById(R.id.search_recycler_view);
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
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
        super.onNewIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }

    private void callMenuActivity(Context c) {
        Intent intent = new Intent(c, MenuActivity.class);
        startActivity(intent);
    }

    private void prepRestaurantData() {
        //current implementation uses test data! something like i->getCachedRestaurants
        for (int i = 0; i < 5; i++) {
            restaurantList.add(new Restaurant(SearchRestaurantActivity.this));
        }
        rAdapter.notifyDataSetChanged();
    }
}
