package com.example.android.tabl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.tabl.table_recyclerview.Table;
import com.example.android.tabl.table_recyclerview.TableAdapter;
import com.example.android.tabl.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SelectTableActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TableAdapter tableAdapter;

    private List<Table> tableList = new ArrayList<>();
    private final String TABLE_TITLE = "Table ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        // top action bar text is renamed and a 'close' symbol is enabled

        /*
        this.getSupportActionBar().setTitle("Select Table");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
        */

        tableList = new ArrayList<>();
        recyclerView = findViewById(R.id.table_recycler_view);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this,recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        tableList.get(position);
                        //go back to basket/checkout view, passing table data
                    }
                    @Override public void onLongItemClick(View view, int position) {
                    }
                })
        );

        tableList.add(new Table("test"));
        tableList.add(new Table("test"));

        tableAdapter = new TableAdapter(tableList);
        RecyclerView.LayoutManager tLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        recyclerView.setLayoutManager(tLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tableAdapter);
        prepTableData();
    }

    public void prepTableData(){
        int numOfTables = getNumOfTables();
        for(int i = 1; i <= numOfTables; i++){
            tableList.add(new Table(TABLE_TITLE+i));
        }
        tableAdapter.notifyDataSetChanged();
    }

    //TEST DATA
    public int getNumOfTables(){
        return 10;
    }
}
