package com.example.team.foodie;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FridgeItem {
    private int quantity = 0;
    private String mName;

    public FridgeItem(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void incrementQuantity(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity, view);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrementQuantity(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity, view);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number, View view) {
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}
