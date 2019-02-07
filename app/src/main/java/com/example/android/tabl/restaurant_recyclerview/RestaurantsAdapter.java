package com.example.android.tabl.restaurant_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;

import java.util.List;


public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.MyViewHolder>{

    private List<Restaurant> restaurantsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView restaurant_name, restaurant_address, restaurant_distance_from_user;

        public MyViewHolder(View view) {
            super(view);
            restaurant_name = view.findViewById(R.id.restaurant_name);
            restaurant_address = view.findViewById(R.id.restaurant_address);
            restaurant_distance_from_user = view.findViewById(R.id.restaurant_distance_from_user);
        }
    }

    public RestaurantsAdapter(List<Restaurant> restaurantsList){
        this.restaurantsList = restaurantsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.find_restaurant_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantsList.get(position);
        holder.restaurant_name.setText(restaurant.getName());
        holder.restaurant_address.setText(restaurant.getAddress());
        holder.restaurant_distance_from_user.setText(restaurant.getDistanceFromUser());
    }

    @Override
    public int getItemCount() {
        return restaurantsList.size();
    }
}
