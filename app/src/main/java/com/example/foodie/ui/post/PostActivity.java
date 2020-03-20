package com.example.foodie.ui.post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodie.MainActivity;
import com.example.foodie.ui.database.DatabaseRequests;
import com.google.firebase.auth.FirebaseAuth;
import com.example.foodie.R;

public class PostActivity extends AppCompatActivity {

    private EditText RecipeName, RecipeIngredients, RecipeDescription, RecipeInstructions, IMG, VID;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mAuth = FirebaseAuth.getInstance();

        final RadioGroup category = (RadioGroup) findViewById(R.id.RadioGroup);
        View Other = category.findViewById(R.id.radioButton1);
        View Vegetarian = category.findViewById(R.id.radioButton2);
        View Quick = category.findViewById(R.id.radioButton3);
        View Healthy = category.findViewById(R.id.radioButton4);

        RecipeName = (EditText) findViewById(R.id.title_text);
        RecipeIngredients = (EditText) findViewById(R.id.ingredients_text);
        RecipeInstructions = (EditText) findViewById(R.id.instructions_text);
        RecipeDescription = (EditText) findViewById(R.id.description_text);

        Button uploadButton = findViewById(R.id.upload_button);
        Button backButton = findViewById(R.id.back_button);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            String checked;
            @Override
            public void onClick(View view) {
                switch (category.getCheckedRadioButtonId()) {
                    case R.id.radioButton1:
                        checked = "Other";
                        break;
                    case R.id.radioButton2:
                        checked = "Vegetarian";
                        break;
                    case R.id.radioButton3:
                        checked = "Quick";
                        break;
                    case R.id.radioButton4:
                        checked = "Healthy";
                        break;
                }

                final String title = RecipeName.getText().toString();
                final String category = checked;
                final String ingredients = RecipeIngredients.getText().toString();
                final String instructions = RecipeInstructions.getText().toString();
                final String description = RecipeDescription.getText().toString();
                final String creator = mAuth.getCurrentUser().getUid();


                if(title.isEmpty() || category.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
                    // Notify the user if not all fields are filled in
                    Toast.makeText(PostActivity.this, "Please fill in all fields",
                            Toast.LENGTH_LONG).show();
                } else {
                    // Else, upload the recipe to the database
                    DatabaseRequests dbReq = new DatabaseRequests();
                    dbReq.addRecipeToDatabase(title, category, ingredients, instructions, null, null, creator, description);
                    // Return to the main screen
                    updateUI();
                }
            }

        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });
    }

    private void updateUI() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
