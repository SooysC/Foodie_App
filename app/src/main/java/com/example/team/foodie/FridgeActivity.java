package com.example.team.foodie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.team.foodie.MainActivity;
import com.example.team.foodie.R;

import java.util.ArrayList;

public class FridgeActivity extends AppCompatActivity implements FridgeAdapter.ICloseFABMenuCallback {

    private FloatingActionButton fabNewItem, fabManualEntry, fabBarcodeEntry, fabImageEntry;
    private LinearLayout fabManualEntryLayout, fabBarcodeEntryLayout, fabImageEntryLayout;
    private TextView textManualEntry, textBarcodeEntry, textImageEntry;
    private boolean isFABOpen = false;
    public static boolean pIsFABOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);

        textManualEntry = (TextView) findViewById(R.id.text_manual_entry);
        textManualEntry.setVisibility(View.GONE);

        textBarcodeEntry = (TextView) findViewById(R.id.text_barcode_entry);
        textBarcodeEntry.setVisibility(View.GONE);

        textImageEntry = (TextView) findViewById(R.id.text_image_entry);
        textImageEntry.setVisibility(View.GONE);

        fabNewItem = (FloatingActionButton) findViewById(R.id.fab_new_item);

        fabManualEntryLayout = (LinearLayout) findViewById(R.id.fab_manual_entry_layout);
        fabManualEntry = (FloatingActionButton) findViewById(R.id.fab_manual_entry);

        fabBarcodeEntryLayout = (LinearLayout) findViewById(R.id.fab_barcode_entry_layout);
        fabBarcodeEntry = (FloatingActionButton) findViewById(R.id.fab_barcode_entry);

        fabImageEntryLayout = (LinearLayout) findViewById(R.id.fab_image_entry_layout);
        fabImageEntry = (FloatingActionButton) findViewById(R.id.fab_image_entry);

        fabNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view_screen = findViewById(R.id.shadowView);
                if (!isFABOpen) {
                    showFABMenu(view_screen);
                } else {
                    closeFABMenu(view_screen);
                }
            }
        });

        fabManualEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FridgeActivity.this, FridgeItemActivity.class);
                startActivity(intent);
            }
        });

        fabBarcodeEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FridgeActivity.this, FridgeItemActivity.class);
                startActivity(intent);
            }
        });


        fabImageEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FridgeActivity.this, FridgeItemActivity.class);
                startActivity(intent);
            }
        });

        // Create a list of FridgeItems
        final ArrayList<FridgeItem> items = new ArrayList<>(); // final so you can use in on click
        items.add(new FridgeItem("hi"));
        items.add(new FridgeItem("bye"));
        items.add(new FridgeItem("see you later aligator! see you later aligator!"));


        // Create an {@link FridgeAdapter}, whose data source is a list of {@link FridgeItem}s. The
        // adapter knows how to create list items for each item in the list.
        FridgeAdapter adapter = new FridgeAdapter(this, items, findViewById(R.id.root));

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // fridge_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.fridge_list);

        // Make the {@link ListView} use the {@link FridgeAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Item} in the list.
        listView.setAdapter(adapter);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View view_screen = findViewById(R.id.shadowView);
                if (isFABOpen) {
                    closeFABMenu(view_screen);
                }
                return true;
            }
        });

        // CAN'T DO THIS WHEN YOU HAVE FOCUSABLE VIEWS IN LIST VIEW ITEM LIKE EDIT TEXT
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, ItemActivity.class);
               startActivity(intent);
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        if(!isFABOpen){
            super.onBackPressed();
        }else{
            View view_screen = findViewById(R.id.shadowView);
            closeFABMenu(view_screen);
        }
    }

    private void showFABMenu(View view_screen) {
        isFABOpen = true;
        pIsFABOpen = isFABOpen;

        view_screen.setVisibility(View.VISIBLE);

        fabManualEntryLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_95));
        textManualEntry.setVisibility(View.VISIBLE);

        fabBarcodeEntryLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_65));
        fabBarcodeEntryLayout.animate().translationX(-getResources().getDimension(R.dimen.standard_60));
        textBarcodeEntry.setVisibility(View.VISIBLE);

        fabImageEntryLayout.animate().translationX(-getResources().getDimension(R.dimen.standard_95));
        textImageEntry.setVisibility(View.VISIBLE);
    }

    public void closeFABMenu(View view_screen) {
        isFABOpen = false;
        pIsFABOpen = isFABOpen;

        view_screen.setVisibility(View.GONE);

        fabManualEntryLayout.animate().translationY(0);
        textManualEntry.setVisibility(View.GONE);

        fabBarcodeEntryLayout.animate().translationY(0);
        fabBarcodeEntryLayout.animate().translationX(0);
        textBarcodeEntry.setVisibility(View.GONE);

        fabImageEntryLayout.animate().translationY(0);
        fabImageEntryLayout.animate().translationX(0);
        textImageEntry.setVisibility(View.GONE);
    }
}
