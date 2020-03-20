package com.example.foodie.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodie.ui.database.DatabaseRequests;

import java.util.List;
import java.util.Map;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
//        DatabaseRequests dbReq = new DatabaseRequests();
//        dbReq.getAllRecipes();
//        while (!dbReq.isFinished()) {
//
//        }
//        List<Map<String, Object>> recipes = dbReq.foundRecipes;
//        mText.setValue(Integer.toString(recipes.size()));
    }

    public LiveData<String> getText() {
        return mText;
    }
}