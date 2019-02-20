package com.example.android.tabl;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {


    private List<MenuListActvity> menulist = new ArrayList<>();

    public RecyclerAdapter(List<MenuListActvity> menulist){

        this.menulist = menulist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return menulist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public myViewHolder(View ViewHolder){

            super(itemView);
        }



    }
}



