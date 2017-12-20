package com.example.jana.myrecipes.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.jana.myrecipes.R;
import com.example.jana.myrecipes.models.RecipeModel;
import com.example.jana.myrecipes.models.RecipesListModel;

/**
 * Created by Jana on 19-Dec-17.
 */

public class RecipesAdapter extends BaseExpandableListAdapter implements ExpandableListAdapter {

    private Context context;
    private RecipesListModel recipes;

    public RecipesAdapter(Context context, RecipesListModel recipes) {
        this.context = context;
        this.recipes = recipes;
    }

    @Override
    public int getGroupCount() {
        if (recipes == null) {
            return 0;
        } else
            return recipes.recipes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return recipes.recipes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return recipes.recipes.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String title = recipes.recipes.get(groupPosition).recipeName;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.layout_recipe_group, null);
        }

        TextView textViewHeader = (TextView) convertView.findViewById(R.id.recipeName);
        textViewHeader.setTypeface(null, Typeface.BOLD);
        textViewHeader.setText(title);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final RecipeModel recipeModel = recipes.recipes.get(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_recipe_child, null);
        }

        TextView recipeTime = (TextView) convertView.findViewById(R.id.recipeTime);
        TextView recipeIngredients = (TextView) convertView.findViewById(R.id.recipeIngredients);
        TextView recipeDescription = (TextView) convertView.findViewById(R.id.recipeDescription);

        recipeTime.setText("Time: " + recipeModel.recipeTime);
        recipeIngredients.setText("Ingredients: " + recipeModel.recipeIngredients);
        recipeDescription.setText(recipeModel.recipeDescription);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public int getRecipePosition(String id, RecipesListModel list){

        for(int i = 0; i<list.recipes.size(); i++){
            if(list.recipes.get(i).Id.equals(id)){
                return i;
            }
        }
        return -1;
    }
}
