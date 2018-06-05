package com.example.team.foodie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecipeIngredientsFragment extends Fragment{

    public RecipeIngredientsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_ingredients, container, false);

        //create list of ingredients
        //final ArrayList<Ingredient> ingredients = new ArrayList<> ();
        return rootView;
    }
}
