package com.example.foodie.ui.database;

import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseRequests {
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    List<Map<String, Object>> foundRecipes;
    boolean finished;


    /**
     * Add recipe to the database
     * @param title title of the recipe
     * @param ingredients ingredients of the recipe
     * @param instructions instructions of the recipe
     * @param imageUri the image of the recipe
     * @param videoUri a video of the recipe
     * @param creator the creator of the recipe
     */
    public void addRecipeToDatabase(String title, String category, String ingredients, String instructions, Uri imageUri, Uri videoUri, String creator, String description) {
        Map<String, Object> recipeData = new HashMap<>();
        recipeData.put("title", title);
        recipeData.put("ingredients", ingredients);
        recipeData.put("instructions", instructions);
        recipeData.put("category", category);
        recipeData.put("creator", creator);
        recipeData.put("likes", 0);
        recipeData.put("description", description);

        String id = title + "," + creator;

        // Add image to storage
        if (imageUri != null) {
            StorageReference imageRef = storage.getReference().child("images");
            final StorageReference imageFilePath = imageRef.child(imageUri.getLastPathSegment());
            imageFilePath.putFile(imageUri);
            recipeData.put("image", imageFilePath);
        }

        // Add video to storage
        if (imageUri != null) {
            StorageReference videoRef = storage.getReference().child("videos");
            final StorageReference videoFilePath = videoRef.child(videoUri.getLastPathSegment());
            videoFilePath.putFile(videoUri);
            recipeData.put("video", videoFilePath);
        }

        db.collection("recipes").document(id).set(recipeData);

        // Add recipe to user recipes list
        db.collection("users").document(creator).update("recipes", FieldValue.arrayUnion(id));
    }

    /**
     * Increases the number of likes on a recipe, and adds a recipe to the liked recipe list of a user
     * @param recipeId id of the recipe to be liked
     * @param userId id of the user liking the recipe
     */
    public void likeRecipe(String recipeId, String userId) {
        // Find recipe and user
        DocumentReference recipeRef = db.collection("recipes").document(recipeId);
        DocumentReference userRef = db.collection("users").document(userId);

        // Increment likes of recipe
        recipeRef.update("likes", FieldValue.increment(1));

        // Add recipe to liked recipes of the user
        userRef.update("liked_recipes", FieldValue.arrayUnion("recipeId"));
    }

    public void addUserToDatabase(String id, String name, String email) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", name);
        userData.put("email", email);
        userData.put("followers", Arrays.asList());
        userData.put("following", Arrays.asList());
        userData.put("recipes", Arrays.asList());
        userData.put("liked_recipes", Arrays.asList());

        db.collection("users").document(id).set(userData);
    }

    /**
     * Gets all recipes from the database and stores them in the local foundRecipes variable
     */
    public void getAllRecipes() {
        finished = false;
        foundRecipes.clear();
        db.collection("recipes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                foundRecipes.add(document.getData());
                            }
                        } else {
                            Log.d("error", "Error getting documents: ", task.getException());
                        }
                    }
                });
        finished = true;
    }

    /**
     * Gets all recipes from a given user and stores them in the local foundRecipes variable
     * @param userId the user's id
     */
    public void getRecipesOfUser(String userId) {
        finished = false;
        foundRecipes.clear();
        db.collection("recipes")
                .whereEqualTo("creator", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                foundRecipes.add(document.getData());
                            }
                        } else {
                            Log.d("error", "Error getting documents: ", task.getException());
                        }
                    }
                });
        finished = true;
    }

    /**
     * Adds the user's id to the correct follower/following lists
     * @param follower user id of the follower
     * @param following user id of the user they are following
     */
    public void follow(String follower, String following) {
        // Find users
        DocumentReference followerRef = db.collection("users").document(follower);
        DocumentReference followingRef = db.collection("users").document(following);

        // Add them to the respective lists
        followerRef.update("following", FieldValue.arrayUnion(following));
        followingRef.update("followers", FieldValue.arrayUnion(follower));
    }

    /**
     * Removes the user's id from the correct follower/following lists
     * @param follower user id of the follower
     * @param following user id of the user they are following
     */
    public void unfollow(String follower, String following) {
        // Find users
        DocumentReference followerRef = db.collection("users").document(follower);
        DocumentReference followingRef = db.collection("users").document(following);

        // Remove them from the respective lists
        followerRef.update("following", FieldValue.arrayRemove(following));
        followingRef.update("followers", FieldValue.arrayRemove(follower));
    }
}
