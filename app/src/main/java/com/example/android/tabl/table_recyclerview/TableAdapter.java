package com.example.android.tabl.table_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tabl.R;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {

   private List<Table> tableList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView table_name;

        public MyViewHolder(View view) {
            super(view);
            table_name = view.findViewById(R.id.table_name);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(parent.getContext())
               .inflate(R.layout.activity_select_table, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Table table = tableList.get(position);
    }

    @Override
    public int getItemCount() {
        return tableList.size();
    }
}