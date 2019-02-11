package com.example.android.tabl;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.example.android.tabl.table_recyclerview.TableItem;

import java.util.ArrayList;
import java.util.List;
public class SelectTableActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<TableItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        // top action bar text is renamed and a 'close' symbol is enabled
        this.getSupportActionBar().setTitle("Select Table");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        // top action bar text is renamed and a 'close' symbol is enabled
        this.getSupportActionBar().setTitle("Select Table");
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<TableItem>();

        for (int testInt = 0; testInt < 10; testInt++) ;{
            TableItem tableItem = new TableItem("Heading" + Integer.toString(testInt+1));

             listItems.add(tableItem);

             //adapter = new Adapter(listItems, this);

             recyclerView.setAdapter(adapter);

        }
    }
}
