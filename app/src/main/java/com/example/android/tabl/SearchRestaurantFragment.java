package com.example.android.tabl;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
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

public class SearchRestaurantFragment extends FragmentActivity {

    private RecyclerView recyclerView;
    private List<Restaurant> restaurantList = new ArrayList<>();
    private RestaurantsAdapter rAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_restaurant);

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
    }

    private void callMenuActivity(Context c){

    }

    private void prepRestaurantData() {
        //current implementation uses test data! something like i->getCachedRestaurants
        for (int i = 0; i < 5; i++) {
            restaurantList.add(new Restaurant(SearchRestaurantFragment.this));
        }
        rAdapter.notifyDataSetChanged();
    }
}
