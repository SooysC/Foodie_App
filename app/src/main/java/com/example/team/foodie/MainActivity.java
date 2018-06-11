package com.example.team.foodie;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initial homepage fragment
        Fragment fragment = new MainFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.screen_area, fragment, Integer.toString(R.id.nav_home));

        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (FridgeFragment.pIsFABOpen) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FridgeFragment fridgeFragment = (FridgeFragment) fragmentManager.findFragmentByTag(Integer.toString(R.id.nav_my_fridge));
            fridgeFragment.closeFABMenu(findViewById(R.id.root).findViewById(R.id.shadowView));
        }
        else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new MainFragment();
        } else if (id == R.id.nav_my_fridge) {
            fragment = new FridgeFragment();
        } else if (id == R.id.nav_groceries) {

        } else if (id == R.id.nav_settings) {

        }

        //catch null cases if necessary
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (fragmentManager.getBackStackEntryCount() > 0) {
                // only add current fragment to back stack if it was not recently added
                // (avoiding multiple instances of same fragment in back stack)
                String previousBackStackEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                String currentBackStackEntry = Integer.toString(id);
                if (!previousBackStackEntry.equals(currentBackStackEntry)) {
                    ft.replace(R.id.screen_area, fragment, Integer.toString(id));
                    // never add home/ main fragment to back stack because
                    // once you are at home screen back should take you out of app
                    // therefore clear entire back stack if you are at home
                    if (id != R.id.nav_home) ft.addToBackStack(Integer.toString(id));
                    else fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
            } else {
                ft.replace(R.id.screen_area, fragment, Integer.toString(id));
                // never add home/ main fragment to back stack because
                // once you are at home screen back should take you out of app
                // therefore clear entire back stack if you are at home
                if (id != R.id.nav_home) ft.addToBackStack(Integer.toString(id));
                else fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
