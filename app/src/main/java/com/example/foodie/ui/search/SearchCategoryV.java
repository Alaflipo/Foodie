package com.example.foodie.ui.search;


import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodie.R;

import java.util.ArrayList;

public class SearchCategoryV extends AppCompatActivity {
    ListView listView;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<SearchViewModel> arrayList = new ArrayList<>();
    com.example.foodie.ui.search.ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_category);

        //requirement is for the toolbar (back button)
        Toolbar toolBar = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolBar = (Toolbar) findViewById(R.id.toolbar);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setActionBar(toolBar);
        }

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        title = new String[]{"Healthy", "Fast", "Meat", "Vegetarian", "Vegan"};
        description = new String[]{"Healthy Recipe details...", "Fast Recipe details...", "Meat Recipe details...", "Vegetarian Recipe details...", "Vegan Recipe details..."};
        icon = new int[]{R.drawable.search_icon, R.drawable.search_icon, R.drawable.search_icon, R.drawable.search_icon, R.drawable.search_icon};


        listView = (ListView) findViewById(R.id.list_healthy);
        listView.setVisibility(ListView.VISIBLE);
        for (int i = 0; i < title.length; i++) {
            SearchViewModel searchViewModel = new SearchViewModel(title[i], description[i], icon[i]);
            //bind all strings in an array that contain the word vegetarian (needs to be adjusted to our database)
            if(title[i].contains("vegetarian") || title[i].contains("Vegetarian") || description[i].contains("vegetarian") || description[i].contains("Vegetarian")) {
                arrayList.add(searchViewModel);
            }
        }

        //pass results to listViewAdapter class
        adapter = new ListViewAdapter(this, arrayList);

        //bind the adapter to the listview
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // //go back to the search page
    }
}

