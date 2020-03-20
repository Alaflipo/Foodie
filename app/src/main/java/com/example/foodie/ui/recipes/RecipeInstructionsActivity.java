package com.example.foodie.ui.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodie.R;

public class RecipeInstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_instructions);
    }

    public void nextActivity(View v){
        Intent i = new Intent(this, VideoScreenActivity.class);
        startActivity(i);
    }
}
