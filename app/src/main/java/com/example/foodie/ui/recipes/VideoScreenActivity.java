package com.example.foodie.ui.recipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodie.R;

public class VideoScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_screen);
    }
    public void nextActivity(View v){
        Intent i = new Intent(this, RecipeInstructionsActivity.class);
        startActivity(i);
    }
}
