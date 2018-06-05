package com.example.team.foodie;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RecipeTabAdapter extends FragmentPagerAdapter {
    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link RecipeTabAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter across swipes.
     */

    public RecipeTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new RecipeIngredientsFragment();
        } else {
            return new RecipeInstructionsFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0) {
            return "Ingredients";
        } else {
            return "Instructions";
        }
    }
}
