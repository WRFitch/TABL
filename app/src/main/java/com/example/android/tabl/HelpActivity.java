package com.example.android.tabl;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import com.example.android.tabl.HelpExpandableList.HelpExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    private ImageButton closeHelpActivityButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);

        listView = (ExpandableListView) findViewById(R.id.faq);
        initData();
        listAdapter = new HelpExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);

        // essentially a 'back' button - will go to previous activity
        closeHelpActivityButton = findViewById(R.id.closeHelpActivityButton);
        closeHelpActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(HelpActivity.this, MoreActivity.class));
                finish();
            }
        });
    }

    private void initData(){
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("First Question");
        listDataHeader.add("second Question");
        listDataHeader.add("third Question");
        listDataHeader.add("Fourth Question");
        listDataHeader.add("Fifth Question");

        List<String> questionOne = new ArrayList<>();
        questionOne.add("Answer for questionOne");

        List<String> questionTwo = new ArrayList<>();
        questionOne.add("Answer for questionTwo");
        questionOne.add("Answer for questionTwo");
        questionOne.add("Answer for questionTwo");

        List<String> questionThird = new ArrayList<>();
        questionOne.add("Answer for questionThree");
        questionOne.add("Answer for questionThree");
        questionOne.add("Answer for questionThree");

        listHash.put(listDataHeader.get(0),questionOne);
        listHash.put(listDataHeader.get(1),questionTwo);
        listHash.put(listDataHeader.get(2),questionThird);

    }
}
