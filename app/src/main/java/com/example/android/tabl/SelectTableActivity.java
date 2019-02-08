package com.example.android.tabl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class SelectTableActivity extends AppCompatActivity {

    listView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);

        listView=(ListView)findViewById(R.id.listView);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Table 1");
        arrayList.add("Table 2");
        arrayList.add("Table 3");
        arrayList.add("Table 4");
        arrayList.add("Table 5");
        arrayList.add("Table 6");
        arrayList.add("Table 7");
        arrayList.add("Table 8");
        arrayList.add("Table 9");
        arrayList.add("Table 10");
        arrayList.add("Table 11");
        arrayList.add("Table 12");


        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.setOnItemClickListner() {

        }

    }
}
