package com.example.team.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//--------------------main page-----------------------------------//
        // Create a list of mainPageRecipes
        final ArrayList<MainPageRecipe> mainPageRecipes = new ArrayList<MainPageRecipe>();
        mainPageRecipes.add(new MainPageRecipe("Recipe One", "1", R.drawable.smoothie));
        mainPageRecipes.add(new MainPageRecipe("Recipe Two", "2", R.drawable.avocadotoast));
        mainPageRecipes.add(new MainPageRecipe("Recipe Three", "3", R.drawable.eggsbenedict));
        mainPageRecipes.add(new MainPageRecipe("Recipe Four", "4", R.drawable.smokedsalmon));
        mainPageRecipes.add(new MainPageRecipe("Recipe Five", "5", R.drawable.smoothie));
        mainPageRecipes.add(new MainPageRecipe("Recipe Six", "6", R.drawable.avocadotoast));
        mainPageRecipes.add(new MainPageRecipe("Recipe Seven", "7", R.drawable.eggsbenedict));
        mainPageRecipes.add(new MainPageRecipe("Recipe Eight", "8", R.drawable.smokedsalmon));
        mainPageRecipes.add(new MainPageRecipe("Recipe Nine", "9", R.drawable.smoothie));
        mainPageRecipes.add(new MainPageRecipe("Recipe Ten", "10", R.drawable.avocadotoast));

        MainPageRecipeAdapter adapter =
                new MainPageRecipeAdapter(getActivity(), mainPageRecipes);

        ListView listView = (ListView) view.findViewById(R.id .recipe_list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                startActivity(intent);
            }
        });

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of mainPageRecipes.
        listView.setAdapter(adapter);

        //--------------------recipe page-----------------------------------//
        // Set a click listener to go to specific recipe page when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the {@link MainPageRecipe} object at the given position the user clicked on
                MainPageRecipe mainPageRecipe = mainPageRecipes.get(position);

                // Create a new intent to open the {@link RecipeActivity}
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                // add data of which recipe was selected
                //intent.put("recipe", position);

                // Start new recipe Activity
                startActivity(intent);
            }
        });

    }
}
