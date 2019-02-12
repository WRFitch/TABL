package com.example.android.tabl.basket_checkout_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;
import com.example.android.tabl.basket_checkout_recyclerview.BasketCheckout;
import com.example.android.tabl.basket_checkout_recyclerview.BasketCheckoutAdapter;
import com.example.android.tabl.restaurant_recyclerview.RestaurantsAdapter;

import java.util.List;

public class BasketCheckoutAdapter extends RecyclerView.Adapter<BasketCheckoutAdapter.MyViewHolder>{

    private List<BasketCheckout> basketCheckoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name, restaurant_address, restaurant_distance_from_user;

        public MyViewHolder(View view) {
            super(view);
            item_name = view.findViewById(R.id.restaurant_name);
            restaurant_address = view.findViewById(R.id.restaurant_address);
            restaurant_distance_from_user = view.findViewById(R.id.restaurant_distance_from_user);
        }
    }

    public BasketCheckoutAdapter(List<BasketCheckout> basketCheckoutList){
        this.basketCheckoutList = basketCheckoutList;
    }

    @Override
    public BasketCheckoutAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkout_list_item, parent, false);
        return new BasketCheckoutAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BasketCheckoutAdapter.MyViewHolder holder, int position) {
        BasketCheckout restaurant = basketCheckoutList.get(position);
        holder.item_name.setText(restaurant.getName());
        holder.restaurant_address.setText(restaurant.getAddress());
        holder.restaurant_distance_from_user.setText(restaurant.getDistanceFromUser());
    }

    @Override
    public int getItemCount() {
        return basketCheckoutList.size();
    }


}


