package com.example.ahmed.ibake.Database;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "recipe")
public class Recipe {

    @Id(autoincrement = true)
    Long _id;

    @Property(nameInDb = "recipeId")
    Long recipeId;

    @Property(nameInDb = "name")
    String name;

    @Property(nameInDb = "servings")
    int servings;

    @Property(nameInDb = "image")
    String image;

    @Generated(hash = 1253337734)
    public Recipe(Long _id, Long recipeId, String name, int servings,
                  String image) {
        this._id = _id;
        this.recipeId = recipeId;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    @Generated(hash = 829032493)
    public Recipe() {
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
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
}
