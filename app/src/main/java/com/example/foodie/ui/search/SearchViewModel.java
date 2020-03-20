package com.example.foodie.ui.search;

public class SearchViewModel {
    String title;
    String desc;
    int icon;

    //constructor
    public SearchViewModel(String title, String desc, int icon) {
        this.title = title;
        this.desc = desc;
        this.icon = icon;
    }

    //getters


    public String getTitle() {
        return this.title;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getIcon() {
        return this.icon;
    }
}