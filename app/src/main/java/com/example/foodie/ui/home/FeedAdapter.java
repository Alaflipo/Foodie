package com.example.foodie.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.foodie.R;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titleText;
        public TextView description;
        public TextView userNameText;
        public TextView likes;
        public TextView comments;
        public ImageView image;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            titleText = itemView.findViewById(R.id.title_recipe);
            description = itemView.findViewById(R.id.recipe_description);
            userNameText = itemView.findViewById(R.id.userName);
            likes = itemView.findViewById(R.id.like_amount);
            comments = itemView.findViewById(R.id.comment_amount);
            image = itemView.findViewById(R.id.recipe_photo);
        }
    }
    private List<Recipe> mRecipes;

    public FeedAdapter(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View recipeView = inflater.inflate(R.layout.recipe_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(recipeView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(FeedAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Recipe recipe = mRecipes.get(position);

        // Set item views based on your views and data model
        TextView titleText = viewHolder.titleText;
        titleText.setText(recipe.getTitle());
        TextView description = viewHolder.description;
        description.setText(recipe.getDesc());
        TextView userNameText = viewHolder.userNameText;
        userNameText.setText(recipe.getUsername());
        TextView likes = viewHolder.likes;
        likes.setText(Integer.toString(recipe.getLikes()));
        TextView comments = viewHolder.comments;
        comments.setText(Integer.toString(recipe.getComments()));
        ImageView image = viewHolder.image;
        String imageUrl = recipe.getImageUrl();
        image.setImageResource(R.drawable.pasta);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
