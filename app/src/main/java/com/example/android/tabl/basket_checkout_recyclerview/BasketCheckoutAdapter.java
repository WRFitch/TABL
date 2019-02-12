package com.example.android.tabl.basket_checkout_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;
import com.example.android.tabl.basket_checkout_recyclerview.BasketCheckout;
import com.example.android.tabl.basket_checkout_recyclerview.BasketCheckoutAdapter;
import com.example.android.tabl.basket_checkout_recyclerview.BasketCheckoutAdapter;

import java.util.List;

public class BasketCheckoutAdapter extends RecyclerView.Adapter<BasketCheckoutAdapter.MyViewHolder>{

    private List<BasketCheckout> basketCheckoutList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name, item_option, item_price;

        public MyViewHolder(View view) {
            super(view);
            item_name = view.findViewById(R.id.item_name);
            item_option = view.findViewById(R.id.item_option);
            item_price = view.findViewById(R.id.item_price);
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
        BasketCheckout food = basketCheckoutList.get(position);
        holder.item_name.setText(food.getName());
        holder.item_option.setText(food.getOptions());
        holder.item_price.setText(food.getPrice());
    }

    @Override
    public int getItemCount() {
        return basketCheckoutList.size();
    }


}


