package com.example.team.foodie;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecipeInstructionsFragment extends Fragment{

    public RecipeInstructionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_instructions, container, false);

        //create list of ingredients
        //final ArrayList<Ingredient> ingredients = new ArrayList<> ();
        return rootView;
    }
}
