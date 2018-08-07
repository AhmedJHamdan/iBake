package com.example.ahmed.ibake.Database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "steps")
public class RecipeSteps {

    @Id(autoincrement = true)
    Long _id;

    @Property(nameInDb = "recipeid")
    Long recipeId;

    @Property(nameInDb = "stepid")
    int stepId;

    @Property(nameInDb = "shortdesc")
    String shortDescription;

    @Property(nameInDb = "description")
    String description;

    @Property(nameInDb = "vidurl")
    String videoUrl;

    @Property(nameInDb = "thumbnailUrl")
    String thumbnailUrl;

    @Generated(hash = 1813871653)
    public RecipeSteps(Long _id, Long recipeId, int stepId, String shortDescription,
                       String description, String videoUrl, String thumbnailUrl) {
        this._id = _id;
        this.recipeId = recipeId;
        this.stepId = stepId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    @Generated(hash = 1174035494)
    public RecipeSteps() {
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

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
