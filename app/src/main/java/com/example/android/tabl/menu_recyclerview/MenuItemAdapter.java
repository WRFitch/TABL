package com.example.android.tabl.menu_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;

import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MyViewHolder>{

    private List<MenuItem> menuItemList;

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

    public MenuItemAdapter(List<MenuItem> menuItemList){
        this.menuItemList = menuItemList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MenuItem menuItem = menuItemList.get(position);
        holder.menu_item_name.setText(menuItem.getName());
        holder.menu_item_price.setText(Double.toString(menuItem.getPrice()));
        holder.menu_item_description.setText(menuItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }


}

