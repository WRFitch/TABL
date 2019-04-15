package com.example.android.tabl;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.android.tabl.menu_recyclerview.FoodItem;
import com.example.android.tabl.menu_recyclerview.FoodItemAdapter;
import com.example.android.tabl.submenu_recyclerview.SubMenu;
import com.example.android.tabl.submenu_recyclerview.SubMenuAdapter;
import com.example.android.tabl.utils.RecyclerItemClickListener;
import com.example.android.tabl.utils.TablUtils;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    //data variables
    private List<FoodItem> foodItemsList = new ArrayList<>();
    private List<SubMenu> subMenusList = new ArrayList<>();
    private String[] filterList;
    private boolean[] filtersChecked;
    private ArrayList<Integer> mUserItems = new ArrayList<>();

    private ArrayList<FoodItem> bucket = new ArrayList<>();

    private FirebaseFirestore db;
    private String restaurantName;

    //ui variables
    private RecyclerView foodRecyclerView;
    private RecyclerView subMenuRecyclerView;
    private FoodItemAdapter fAdapter;
    private SubMenuAdapter smAdapter;
    private ImageButton filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        restaurantName = getIntent().getExtras().getString("restaurant_name");

        filterList = getResources().getStringArray(R.array.Filter_menu);
        filtersChecked = new boolean[filterList.length];
        filterButton = (ImageButton) findViewById(R.id.filterbtn);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterDialog();
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        foodRecyclerView = findViewById(R.id.menu_recycler_view);
        foodRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, foodRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                showAddItemDialog(foodItemsList.get(position));
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //perhaps use this to display item info/add to favourites?
                                TablUtils.functionNotImplemented(view, "maybe add to favourites?");
                            }
                        })
        );

        fAdapter = new FoodItemAdapter(foodItemsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(
                getApplicationContext());
        foodRecyclerView.setLayoutManager(mLayoutManager);
        foodRecyclerView.setItemAnimator(new DefaultItemAnimator());
        foodRecyclerView.setAdapter(fAdapter);
        prepFoodMenuData();

        subMenuRecyclerView = findViewById(R.id.submenu_recycler_view);
        subMenuRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, subMenuRecyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //display selected menu
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }
                        })
        );

        smAdapter = new SubMenuAdapter(subMenusList);
        RecyclerView.LayoutManager sMenuLayoutManager = new LinearLayoutManager(
                getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        subMenuRecyclerView.setLayoutManager(sMenuLayoutManager);
        subMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());
        subMenuRecyclerView.setAdapter(smAdapter);
        prepSubmenuData();
    }

    private void updateMenuData(String restaurantName) {
        if (!TablUtils.isNetworkAvailable(this)) {
            TablUtils.errorMsg(filterButton, "No Internet Connection!");
            return;
        }
        prepSubmenuData();
    }

    private void prepSubmenuData() {
        db = FirebaseFirestore.getInstance();
        DocumentReference menuRef = db.document("Menus/"+ restaurantName);
        //Query submenuQuery = menuRef.get()


        for (int i = 0; i < 20; i++) {
            subMenusList.add(new SubMenu());
        }
        smAdapter.notifyDataSetChanged();
    }

    private void prepFoodMenuData() {
        for (int i = 0; i < 20; i++) {
            foodItemsList.add(new FoodItem());
        }
        fAdapter.notifyDataSetChanged();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_more:
                            // open MoreActivity
                            Intent moreIntent = new Intent(getApplicationContext(),
                                MoreActivity.class);
                            moreIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(moreIntent);
                            break;
                        case R.id.nav_location:
                            // go back to previous activity
                            // downloaded data should be cached automatically, making any
                            // caching method here unnecessary
                            Intent fraIntent = new Intent(getApplicationContext(),
                                    FindRestaurantActivity.class);
                            fraIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(fraIntent);
                            break;
                        case R.id.nav_basket:
                            // open BasketActivity
                            Intent basketIntent = new Intent(getApplicationContext(),
                                    BasketActivity.class);
                            basketIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(basketIntent);
                            break;
                    }
                    return true;

                }
            };

    private ArrayList<FoodItem> getSubMenu(String subMenuName) {
        return null;
    }

    private void showFilterDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MenuActivity.this);
        mBuilder.setTitle(R.string.dialog_title);
        mBuilder.setMultiChoiceItems(filterList, filtersChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if (isChecked){
                    if (! mUserItems.contains(position)){
                        mUserItems.add(position);
                    }
                }
                else if (mUserItems.contains(position)){
                    mUserItems.remove(mUserItems.indexOf(position));
                }
            }
        });
        mBuilder.setCancelable(true);
        mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                //where we filter out the menu based on user selection
            }
        });
        mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                mUserItems.clear();
                for (int i = 0; i < filtersChecked.length; i++){
                    filtersChecked[i] = false;
                }
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    //placeholder method
    private void showAddItemDialog(FoodItem item){
        bucket.add(item);
    }
}
