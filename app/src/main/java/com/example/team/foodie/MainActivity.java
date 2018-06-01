package com.example.team.foodie;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //--------------------main page-----------------------------------//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of mainPageRecipes
        ArrayList<MainPageRecipe> mainPageRecipes = new ArrayList<MainPageRecipe>();
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
                new MainPageRecipeAdapter(this, mainPageRecipes);

        ListView listView = (ListView) findViewById(R.id .recipe_list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of mainPageRecipes.
        listView.setAdapter(adapter);

        // THIS IS TEMPORARY SO I CAN JUST TEST MY CODE FOR NOW
        FloatingActionButton fabTemp = (FloatingActionButton) findViewById(R.id.fab_temp);
        fabTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FridgeActivity.class);
                startActivity(intent);
            }
        });

        //-------------------------------nav bar ----------------------------------//

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
