package com.example.ahmed.ibake.Recipes;


import java.io.Serializable;
import java.util.LinkedList;

public class RecipeData implements Serializable {

    private Long id;
    private String name;
    private int servings;
    private String image;

    private LinkedList<Ingredients> ingredients;
    private LinkedList<Steps> steps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LinkedList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(LinkedList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public LinkedList<Steps> getSteps() {
        return steps;
    }

    public void setSteps(LinkedList<Steps> steps) {
        this.steps = steps;
    }
}
