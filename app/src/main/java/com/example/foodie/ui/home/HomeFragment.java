package com.example.foodie.ui.home;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodie.R;
import com.example.foodie.ui.database.DatabaseRequests;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View root;
    RecyclerView feed;
    DatabaseRequests database;
    List<Map<String, Object>> recipesDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);

//        final TextView textView = root.findViewById(R.id.testText);
//
////        feed = (RecyclerView) root.findViewById(R.id.feed);
////        feed.setLayoutManager(new LinearLayoutManager(getContext()));
////        feed.setHasFixedSize(true);
//
//        homeViewModel.getText().observe(this.getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        recipesDB = new ArrayList<Map<String, Object>>();
        Map<String, Object> recipe1 = new HashMap<>();
        recipe1.put("title", "Boerenkool met worst");
        recipe1.put("instructions", "Gewoon lekker stampen en dan komt het wel goed");
        recipe1.put("imageUrl", "boerenkool.jpg");
        recipe1.put("videoUrl", "nothing");
        recipe1.put("username", "lekkerEten");
        recipe1.put("ingredients", "boerenkool en worst");
        recipe1.put("likes", 1003);

        Map<String, Object> recipe2 = new HashMap<>();
        recipe2.put("title", "Pastaaaaaa");
        recipe2.put("instructions", "pasta in een pan lekeker koken en dingen bij gooien");
        recipe2.put("imageUrl", "pasta.jpg");
        recipe2.put("videoUrl", "nothing");
        recipe2.put("username", "nogLekkerderEten");
        recipe2.put("ingredients", "pasta en extra zooi");
        recipe2.put("likes", 19);

        Map<String, Object> recipe3 = new HashMap<>();
        recipe3.put("title", "wokje");
        recipe3.put("instructions", "weer alles in een pan en wokken maar");
        recipe3.put("imageUrl", "wokje.jpg");
        recipe3.put("videoUrl", "nothing");
        recipe3.put("username", "VeeeeeellLekkerderEten");
        recipe3.put("ingredients", "Goed olie en dan zooi erbij goooien");
        recipe3.put("likes", -204);



        recipesDB.add(recipe1);
        recipesDB.add(recipe2);
        recipesDB.add(recipe3);

        List<Recipe> recipes = convertToRecipeList(recipesDB);

        // Lookup the recyclerview in activity layout
        RecyclerView rvRecipes = (RecyclerView) root.findViewById(R.id.feed);
        rvRecipes.setItemAnimator(new SlideInUpAnimator());

        // Create adapter passing in the sample user data
        FeedAdapter adapter = new FeedAdapter(recipes);
        // Attach the adapter to the recyclerview to populate items
        rvRecipes.setAdapter(adapter);
        // Set layout manager to position the items
        rvRecipes.setLayoutManager(new LinearLayoutManager(getContext()));
        // That's all!

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }

    public List<Recipe> convertToRecipeList(List<Map<String, Object>> recipesDB) {
        List<Recipe> recipes = new ArrayList<>();
        for (Map<String, Object> recipeDB: recipesDB) {
            Recipe recipe = new Recipe( (String) recipeDB.get("title"), (String) recipeDB.get("instructions"),
                    (String) recipeDB.get("imageUrl"), (String) recipeDB.get("videoUrl"), (String) recipeDB.get("username"),
                    (String) recipeDB.get("ingredients"), (String) recipeDB.get("instructions"), (int) recipeDB.get("likes"), 2);
            recipes.add(recipe);
        }
        return recipes;
    }



    @Override
    public void onStart() {
        super.onStart();




        //textView.setText(recipes.get(0).get("ingredients").toString());
    }
}