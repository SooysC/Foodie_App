package com.example.team.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe);

        //---------------------Tab------------------------------//
        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.recipe_viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        RecipeTabAdapter adapter = new RecipeTabAdapter(this, getSupportFragmentManager());

        //set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        //Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.recipe_tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        //---------------------Scroll------------------------------//
        Intent intent = getIntent();

        final Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.recipe_collapsing_toolbar);
        collapsingToolbar.setTitle("Sample 1");

        loadBackdrop();
    }

    private void loadBackdrop() {
        //----------temp---------//
        final ImageView imageView = findViewById(R.id.recipe_backdrop);
        Glide.with(this).load(R.drawable.avocadotoast).apply(RequestOptions.centerCropTransform()).into(imageView);
        //----------temp---------//

        //final ImageView imageView = findViewById(R.id.recipe_backdrop);
        //Glide.with(this).load(Recipes).getDrawable()).apply(RequestOptions.centerCropTransform()).into(imageView);
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    //getMenuInflater().inflate(R.menu.sample_actions, menu);
    //    return true;
    //}
}
