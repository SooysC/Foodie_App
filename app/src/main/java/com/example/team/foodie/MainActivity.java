package com.example.team.foodie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of mainPageRecipes
        ArrayList<MainPageRecipe> mainPageRecipes = new ArrayList<MainPageRecipe>();
        mainPageRecipes.add(new MainPageRecipe("Recipe One", "1", R.drawable.androidparty));
        mainPageRecipes.add(new MainPageRecipe("Recipe Two", "2", R.drawable.number_two));
        mainPageRecipes.add(new MainPageRecipe("Recipe Three", "3", R.drawable.number_three));
        mainPageRecipes.add(new MainPageRecipe("Recipe Four", "4", R.drawable.number_four));
        mainPageRecipes.add(new MainPageRecipe("Recipe Five", "5", R.drawable.number_five));
        mainPageRecipes.add(new MainPageRecipe("Recipe Six", "6", R.drawable.number_six));
        mainPageRecipes.add(new MainPageRecipe("Recipe Seven", "7", R.drawable.number_seven));
        mainPageRecipes.add(new MainPageRecipe("Recipe Eight", "8", R.drawable.number_eight));
        mainPageRecipes.add(new MainPageRecipe("Recipe Nine", "9", R.drawable.number_nine));
        mainPageRecipes.add(new MainPageRecipe("Recipe Ten", "10", R.drawable.number_ten));

        MainPageRecipeAdapter adapter =
                new MainPageRecipeAdapter(this, mainPageRecipes);

        ListView listView = (ListView) findViewById(R.id .recipe_list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of mainPageRecipes.
        listView.setAdapter(adapter);

    }
}
