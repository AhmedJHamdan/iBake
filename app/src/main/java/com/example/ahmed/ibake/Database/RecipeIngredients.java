package com.example.ahmed.ibake.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "ingredients")
public class RecipeIngredients {

    @Id(autoincrement = true)
    Long ingredientId;

    @Property(nameInDb = "recipeid")
    Long recipeId;

    @Property(nameInDb = "quantity")
    double quanity;

    @Property(nameInDb = "measure")
    String measure;

    @Property(nameInDb = "ingredient")
    String ingredient;

    @Generated(hash = 179275204)
    public RecipeIngredients(Long ingredientId, Long recipeId, double quanity,
                             String measure, String ingredient) {
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
        this.quanity = quanity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @Generated(hash = 333700824)
    public RecipeIngredients() {
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public double getQuanity() {
        return quanity;
    }

    public void setQuanity(double quanity) {
        this.quanity = quanity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
