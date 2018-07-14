package com.example.team.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainFragment extends Fragment{
    //filter stuff
    private boolean filterExpanded = false;
    LinearLayout filterAnimation;
    int mOriginalHeight;
    boolean initialSizeObtained = false;

    Animation _hideAnimation = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) filterAnimation.getLayoutParams();
            params.topMargin = -(int) (mOriginalHeight * interpolatedTime);
            filterAnimation.setLayoutParams(params);
            //filterAnimation.setVisibility(View.INVISIBLE);
        }
    };

    Animation _showAnimation = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) filterAnimation.getLayoutParams();
            params.topMargin = (int) (mOriginalHeight * (interpolatedTime - 1));
            filterAnimation.setLayoutParams(params);
            //filterAnimation.setVisibility(View.VISIBLE);
        }
    };

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


        //---------mainPage filter----------------//

        filterAnimation = (LinearLayout) view.findViewById(R.id.filter_expand);
        filterAnimation.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(initialSizeObtained)
                    return;
                initialSizeObtained = true;
                mOriginalHeight = filterAnimation.getMeasuredHeight();
            }
        });

        _hideAnimation.setDuration(2000);
        _showAnimation.setDuration(2000);

        filterAnimation.setVisibility(View.INVISIBLE);

        TextView filterView = view.findViewById(R.id.filter);
        filterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToggleTopBar(view);
            }
        });
    }

    //for mainpage filter
    public void ToggleTopBar(View view) {
        filterExpanded = !filterExpanded;

        filterAnimation.clearAnimation();
        filterAnimation.startAnimation(filterExpanded? _showAnimation : _hideAnimation);
    }
}
