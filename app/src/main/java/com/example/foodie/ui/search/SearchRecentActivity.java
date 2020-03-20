package com.example.foodie.ui.search;

import android.content.SearchRecentSuggestionsProvider;

public class SearchRecentActivity extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY =  "com.example.foodie.ui.search.SearchRecentActivity";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SearchRecentActivity() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
