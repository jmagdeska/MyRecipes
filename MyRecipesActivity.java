package com.example.jana.myrecipes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.jana.myrecipes.adapters.RecipesAdapter;
import com.example.jana.myrecipes.models.RecipesListModel;

import java.util.ArrayList;

/**
 * Created by Jana on 19-Dec-17.
 */

public class MyRecipesActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecipesAdapter adapter;
    private RecipesListModel recipesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        listView = (ExpandableListView) findViewById(R.id.listView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh2);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
//                getRecipes();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

//    public void getRecipes(){
//        recipesList = null;
//        adapter = new RecipesAdapter(this, recipesList, this);
//
//        listView.setAdapter(adapter);
//        if (!eventId.equals("")) {
//            int pos = adapter.get(eventId, recipesList);
//            if (pos != -1)
//                listView.expandGroup(pos);
//        }
//    }
}
