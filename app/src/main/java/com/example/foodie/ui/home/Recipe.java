package com.example.foodie.ui.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Recipe {
    private String title, desc, imageUrl, videoUrl, username, ingredients, instructions;
    private int like_amount, comment_amount;

    public Recipe(String title, String desc, String imageUrl, String videoUrl, String username,
                  String ingredients, String instructions, int like_amount, int comment_amount) {
        this.imageUrl=imageUrl;
        this.videoUrl=videoUrl;
        this.title = title;
        this.desc = desc;
        this.username = username;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.like_amount = like_amount;
        this.comment_amount = comment_amount;
    }

    public Recipe() {
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setLikes(int likes) {
        this.like_amount = likes;
    }

    public int getLikes() {
        return like_amount;
    }

    public void setComments(int comments) {
        this.comment_amount = comments;
    }

    public int getComments() {
        return comment_amount;
    }


}
