package com.example.ahmed.ibake.Recipes;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.content.AsyncTaskLoader;

import com.example.ahmed.ibake.BuildConfig;
import com.example.ahmed.ibake.RecipeApplication;
import com.example.ahmed.ibake.Database.Recipe;
import com.example.ahmed.ibake.Database.RecipeIngredients;
import com.example.ahmed.ibake.Database.RecipeIngredientsDao;
import com.example.ahmed.ibake.Database.RecipeSteps;
import com.example.ahmed.ibake.Database.RecipeStepsDao;
import com.example.ahmed.ibake.Network.RetrofitProvider;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecipeLoader extends AsyncTaskLoader<LinkedList<RecipeData>> {

    public static final int MODE_LIST = 0;
    public static final int MODE_DETAILS = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MODE_LIST, MODE_DETAILS})
    public @interface MODE {}

    private int mode;
    private int findRecipeId;
    private LinkedList<RecipeData> recipeDataList;

    public RecipeLoader(Context context, @MODE int mode, int findRecipeId) {
        super(context);
        this.mode = mode;
        this.findRecipeId = findRecipeId;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if(null != recipeDataList) {
            deliverResult(recipeDataList);
            return;
        }

        forceLoad();
    }

    @Override
    public LinkedList<RecipeData> loadInBackground() {

        //Check if the data is available locally in database
        if(isDataPresentInDatabase()) {
            return getDataFromDatabase();
        }

        Retrofit retrofit = RetrofitProvider.getInstance().getRetrofit(BuildConfig.RECIPE_JSON_URL);
        RecipeApi service = retrofit.create(RecipeApi.class);
        Call<LinkedList<RecipeData>> call = service.downloadRecipe();

        try {

            Response<LinkedList<RecipeData>> data = call.execute();
            recipeDataList = data.body();

            saveDataInDatabase();

        } catch (IOException ioEx) {
            //
        }

        return recipeDataList;
    }

    private boolean isDataPresentInDatabase() {

        RecipeApplication application = ((RecipeApplication) getContext().getApplicationContext());
        return 1 <= application.getDaoSession().getRecipeDao().count();
    }

    private LinkedList<RecipeData> getDataFromDatabase() {

        RecipeApplication application = ((RecipeApplication) getContext().getApplicationContext());
        recipeDataList = new LinkedList<>();

        QueryBuilder<Recipe> qbRecipe = application.getDaoSession().getRecipeDao().queryBuilder();
        List<Recipe> recipeList = qbRecipe.list();

        for(Recipe recipe : recipeList) {

            RecipeData recipeData = new RecipeData();
            recipeData.setId(recipe.get_id());
            recipeData.setImage(recipe.getImage());
            recipeData.setName(recipe.getName());
            recipeData.setServings(recipe.getServings());

            //Prepare for ingredients
            LinkedList<Ingredients> recipeIngredients = new LinkedList<>();
            recipeData.setIngredients(recipeIngredients);
            List<RecipeIngredients> ingredientsList = application.getDaoSession()
                    .getRecipeIngredientsDao().queryBuilder()
                    .where(RecipeIngredientsDao.Properties.RecipeId.eq(recipe.get_id()))
                    .list();

            //Get all the ingredients
            for(RecipeIngredients ingredient : ingredientsList) {

                Ingredients ingred = new Ingredients();
                ingred.setIngredient(ingredient.getIngredient());
                ingred.setMeasure(ingredient.getMeasure());
                ingred.setQuantity(ingredient.getQuanity());

                recipeIngredients.add(ingred);
            }

            //Prepare for steps
            LinkedList<Steps> recipeSteps = new LinkedList<>();
            recipeData.setSteps(recipeSteps);
            List<RecipeSteps> steps = application.getDaoSession().getRecipeStepsDao()
                    .queryBuilder()
                    .where(RecipeStepsDao.Properties.RecipeId.eq(recipe.get_id()))
                    .list();

            for(RecipeSteps resSteps : steps) {

                Steps s = new Steps();
                s.setId(resSteps.getStepId());
                s.setDescription(resSteps.getDescription());
                s.setShortDescription(resSteps.getShortDescription());
                s.setThumbnailUrl(resSteps.getThumbnailUrl());
                s.setVideoUrl(resSteps.getVideoUrl());

                recipeSteps.add(s);
            }

            recipeDataList.add(recipeData);
        }

        return recipeDataList;
    }

    private void saveDataInDatabase() {

        RecipeApplication application = ((RecipeApplication) getContext().getApplicationContext());

        for(RecipeData data : recipeDataList) {

            //Insert the food recipe top level
            application.getDaoSession().getRecipeDao().insert(
                    new Recipe(null, data.getId(), data.getName(), data.getServings(), data.getImage())
            );

            for(Ingredients ingredients : data.getIngredients()) {

                //Insert the ingredients
                application.getDaoSession().getRecipeIngredientsDao().insert(
                        new RecipeIngredients(
                                null, data.getId(), ingredients.getQuantity(),
                                ingredients.getMeasure(), ingredients.getIngredient())
                );
            }

            for(Steps steps : data.getSteps()) {

                //Insert the steps
                application.getDaoSession().getRecipeStepsDao().insert(
                        new RecipeSteps(
                                null, data.getId(), steps.getId(), steps.getShortDescription(),
                                steps.getDescription(), steps.getVideoUrl(),
                                steps.getThumbnailUrl())
                );
            }
        }
    }
}
