package com.example.team.foodie;

public class MainPageRecipe {

    private String mRecipeName;
    private String mCookTimeMin;
    private int mImageResourceId;

    public MainPageRecipe(String recipeName, String cookTimeMin, int imageResourceId){
        mRecipeName = recipeName;
        mCookTimeMin = cookTimeMin + " min";
        mImageResourceId = imageResourceId;
    }

    public String getRecipeName(){
        return mRecipeName;
    }

    public String getCookTimeMin(){
        return mCookTimeMin;
    }

    public int getImageResourceId(){return mImageResourceId;}
}
