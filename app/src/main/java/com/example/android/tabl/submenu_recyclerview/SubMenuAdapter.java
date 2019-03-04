package com.example.android.tabl.submenu_recyclerview;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;

import java.util.List;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.MyViewHolder>{

    private List<SubMenu> subMenuList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView submenu_item_name;

        public MyViewHolder(View view) {
            super(view);
            submenu_item_name = view.findViewById(R.id.submenu_item_name);


        }
    }

    public SubMenuAdapter(List<SubMenu> subMenuList){
        this.subMenuList = subMenuList;
    }

    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.submenu_list,parent,false);


        return new MyViewHolder(itemView);

    }

    public void onBindViewHolder(MyViewHolder holder, int position){

        SubMenu subMenu = subMenuList.get(position);
        holder.submenu_item_name.setText(subMenu.getName());



    }

    public int getItemCount(){

        return subMenuList.size();
    }
}


















