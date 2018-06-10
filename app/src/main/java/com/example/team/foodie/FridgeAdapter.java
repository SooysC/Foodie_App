package com.example.team.foodie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FridgeAdapter extends ArrayAdapter<FridgeItem> {

    public interface ICloseFABMenuCallback {
        void closeFABMenu(View view_screen);
    }

    private ICloseFABMenuCallback callerActivity;
    private View mRoot;
    private Context mContext;

    public FridgeAdapter(Activity context, ArrayList<FridgeItem> items, View root, ICloseFABMenuCallback listener) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, items);
        callerActivity = listener;
        mRoot = root;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fridge_list_item, parent, false);
        }


        final View listItemViewCopy = listItemView;

        //listItemView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        CardView cardView = listItemView.findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view_screen = mRoot.findViewById(R.id.shadowView);
                if (FridgeFragment.pIsFABOpen) {
                    callerActivity.closeFABMenu(view_screen);
                    return;
                }
                Intent intent = new Intent(getContext(), FridgeItemActivity.class);
                getContext().startActivity(intent);
            }
        });

        EditText editText = (EditText) listItemView.findViewById(R.id.quantity_text_view);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view_screen = mRoot.findViewById(R.id.shadowView);
                if (FridgeFragment.pIsFABOpen) {
                    callerActivity.closeFABMenu(view_screen);
                    return true;
                }
                return false;
            }
        });

        // for ignoring clicks
        LinearLayout linLayout = listItemView.findViewById(R.id.quantity_lin_layout);
        linLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view_screen = mRoot.findViewById(R.id.shadowView);
                if (FridgeFragment.pIsFABOpen) {
                    callerActivity.closeFABMenu(view_screen);
                    return true;
                }
                return true;
            }
        });

        TextView textView = listItemView.findViewById(R.id.dummy_text_view_side);
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view_screen = mRoot.findViewById(R.id.shadowView);
                if (FridgeFragment.pIsFABOpen) {
                    callerActivity.closeFABMenu(view_screen);
                    return true;
                }
                return true;
            }
        });

        final FridgeItem currentItem = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.ingredient);
        nameTextView.setText(currentItem.getName());

        Button incrementButton = (Button) listItemView.findViewById(R.id.increment_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view_screen = mRoot.findViewById(R.id.shadowView);
                if (FridgeFragment.pIsFABOpen) {
                    callerActivity.closeFABMenu(view_screen);
                    return;
                }
                currentItem.incrementQuantity(listItemViewCopy);
            }
        });

        Button decrementButton = (Button) listItemView.findViewById(R.id.decrement_button);
        decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view_screen = mRoot.findViewById(R.id.shadowView);
                if (FridgeFragment.pIsFABOpen) {
                    callerActivity.closeFABMenu(view_screen);
                    return;
                }
                if (currentItem.getQuantity() == 0) {
                    CharSequence text = "You cannot have negative items :)";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(mContext, text, duration);
                    toast.show();
                    return;
                }
                currentItem.decrementQuantity(listItemViewCopy);
            }
        });

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }
}
