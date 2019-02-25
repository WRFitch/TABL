package com.example.android.tabl.menu_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.MyViewHolder>{

    private List<FoodItem> foodItemList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView menu_item_name, menu_item_price,
                menu_item_description;

        public MyViewHolder(View view) {
            super(view);
            menu_item_name = view.findViewById(R.id.menu_item_name);
            menu_item_price = view.findViewById(R.id.menu_item_price);
            menu_item_description = view.findViewById(R.id.menu_item_description);
        }
    }

    public FoodItemAdapter(List<FoodItem> foodItemList){
        this.foodItemList = foodItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.menu_item_name.setText(foodItem.getName());
        holder.menu_item_price.setText(Double.toString(foodItem.getPrice()));
        holder.menu_item_description.setText(foodItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }


}

