package com.example.foodie.ui.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.os.ParcelFileDescriptor;
import android.provider.SearchRecentSuggestions;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;

import com.example.foodie.R;

import java.util.ArrayList;

import static androidx.lifecycle.ViewModelProviders.*;
import static com.example.foodie.ui.search.SearchRecentActivity.*;

public class SearchFragment extends Fragment {

    SearchView searchView;
    ListView listView;
    LinearLayout categories;
    LinearLayout filter_page;
    com.example.foodie.ui.search.ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<SearchViewModel> arrayList = new ArrayList<>();

    private SearchViewModel mViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = of(this).get(SearchViewModel.class);
//
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        title = new String[]{"Healthy", "Fast", "Meat", "Vegetarian", "Vegan"};
        description = new String[]{"Healthy Recipe details...", "Fast Recipe details...", "Meat Recipe details...", "Vegetarian Recipe details...", "Vegan Recipe details..."};
        icon = new int[]{R.drawable.search_icon, R.drawable.search_icon, R.drawable.search_icon, R.drawable.search_icon, R.drawable.search_icon};

        listView = (ListView) getView().findViewById(R.id.listOfRecipes);
        searchView = (SearchView) getView().findViewById(R.id.search_bar);
        categories = (LinearLayout) getView().findViewById(R.id.search_categories);
        filter_page = (LinearLayout) getView().findViewById(R.id.filter_page);
        categories.setVisibility(LinearLayout.VISIBLE);
        listView.setVisibility(ListView.GONE);
        filter_page.setVisibility(ListView.GONE);

        for (int i = 0; i < title.length; i++) {
            com.example.foodie.ui.search.SearchViewModel searchViewModel = new com.example.foodie.ui.search.SearchViewModel(title[i], description[i], icon[i]);
            //bind all strings in an array
            arrayList.add(searchViewModel);
        }

        //pass results to listViewAdapter class
        adapter = new com.example.foodie.ui.search.ListViewAdapter(getActivity(), arrayList);

        //bind the adapter to the listview
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getView().findViewById(R.id.listOfRecipes).setVisibility(ListView.VISIBLE);
                getView().findViewById(R.id.filter_page).setVisibility(ListView.GONE);
                if (TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                    getView().findViewById(R.id.listOfRecipes).setVisibility(ListView.GONE);
                    getView().findViewById(R.id.filter_page).setVisibility(ListView.VISIBLE);
                } else {
                    adapter.filter(s);
                }
                return true;
            }
        });
        searchView.setOnSearchClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //show listview only when search bar is clicked
                getView().findViewById(R.id.filter_page).setVisibility(ListView.VISIBLE);
                //make the 4 category buttons disappear
                getView().findViewById(R.id.button_healthy).setVisibility(Button.GONE);
                getView().findViewById(R.id.button_vegetarian).setVisibility(Button.GONE);
                getView().findViewById(R.id.button_quick).setVisibility(Button.GONE);
                getView().findViewById(R.id.button_others).setVisibility(Button.GONE);
            }
        });
        searchView.setOnCloseListener(new OnCloseListener() {
            @Override
            public boolean onClose() {
                //make the listview disappear
                getView().findViewById(R.id.filter_page).setVisibility(ListView.GONE);
                //show categories again when cross in search bar is pressed
                getView().findViewById(R.id.button_healthy).setVisibility(Button.VISIBLE);
                getView().findViewById(R.id.button_vegetarian).setVisibility(Button.VISIBLE);
                getView().findViewById(R.id.button_quick).setVisibility(Button.VISIBLE);
                getView().findViewById(R.id.button_others).setVisibility(Button.VISIBLE);
            return false;
            }
        });

        final int MODE = DATABASE_MODE_QUERIES | DATABASE_MODE_2LINES;
        Intent intent  = getActivity().getIntent();

        //save search history
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getActivity(),
                    AUTHORITY, MODE);
            suggestions.saveRecentQuery(query, null);
        }

        //clear search history
        SearchRecentSuggestions suggestions = new SearchRecentSuggestions(getActivity(),
                AUTHORITY, MODE);
        suggestions.clearHistory();

        //create buttons to other pages
        Button btnHealthy;
        Button btnVegetarian;
        Button btnSQuick;
        Button btnOthers;
        Button btnFilter;

        btnHealthy = (Button) getActivity().findViewById(R.id.button_healthy);
        btnVegetarian = (Button) getActivity().findViewById(R.id.button_vegetarian);
        btnSQuick = (Button) getActivity().findViewById(R.id.button_quick);
        btnOthers = (Button) getActivity().findViewById(R.id.button_others);
        btnFilter = (Button) getActivity().findViewById(R.id.filter_button);

        //create new activity when healthy category button is pressed
        btnHealthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inH = new Intent(getActivity(), SearchCategoryH.class);
                startActivity(inH);
            }
        });

        //create new activity when vegetarian category button is pressed
        btnVegetarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inV = new Intent(getActivity(), SearchCategoryV.class);
                startActivity(inV);
            }
        });

        //create new activity when something quick category button is pressed
        btnSQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inQ = new Intent(getActivity(), SearchCategoryQ.class);
                startActivity(inQ);
            }
        });

        //create new activity when others... category button is pressed
        btnOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inO = new Intent(getActivity(), SearchCategoryO.class);
                startActivity(inO);
            }
        });

        //create new activity when healthy category button is pressed
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inF = new Intent(getActivity(), SearchFilter.class);
                startActivity(inF);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter_button) {
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
    }
}


