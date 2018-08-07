package com.example.ahmed.ibake;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.ahmed.ibake.RecipeDetails.RecipeDetailsFragment;
import com.example.ahmed.ibake.RecipeList.RecipeListFragment;
import com.example.ahmed.ibake.Recipes.RecipeData;

public class RecipeListActivity extends AppCompatActivity {

    private static final String TAG_FRAG_LIST = "listFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        RecipeListFragment recipeListFragment = (RecipeListFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_FRAG_LIST);

        if(null == recipeListFragment) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            recipeListFragment = new RecipeListFragment();
            transaction.add(R.id.fl_recipe_list, recipeListFragment, TAG_FRAG_LIST);
            transaction.commit();
        }

        recipeListFragment.setIRecipeAction(action);
    }

    private RecipeListFragment.IRecipeAction action = new RecipeListFragment.IRecipeAction() {
        @Override
        public void onRecipeClicked(RecipeData recipeData) {

            Intent detailsIntent = new Intent(getApplicationContext(), RecipeDetailsActivity.class);
            detailsIntent.putExtra(RecipeDetailsFragment.KEY_RECIPE_DATA_OBJ, recipeData);
            startActivity(detailsIntent);
        }
    };
}
