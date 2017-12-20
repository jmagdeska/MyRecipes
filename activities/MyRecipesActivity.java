package com.example.jana.myrecipes.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.example.jana.myrecipes.R;
import com.example.jana.myrecipes.adapters.RecipesAdapter;
import com.example.jana.myrecipes.db.RecipeDetailsDB;
import com.example.jana.myrecipes.models.RecipesListModel;

/**
 * Created by Jana on 19-Dec-17.
 */

public class MyRecipesActivity extends AppCompatActivity {

    private Context context;
    private SharedPreferences prefs;
    private RecipeDetailsDB recipeDetailsDB;
    private ExpandableListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecipesAdapter adapter;
    private RecipesListModel recipesList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        context = MyRecipesActivity.this;

        listView = (ExpandableListView) findViewById(R.id.listView);
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        getRecipes();
    }

    public void getRecipes(){
        recipeDetailsDB = new RecipeDetailsDB(context);
        recipesList = recipeDetailsDB.getRecipes();
        adapter = new RecipesAdapter(this, recipesList);
        listView.setAdapter(adapter);
    }
}
