package com.example.ahmed.ibake;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.ahmed.ibake.RecipeDetails.RecipeDetailsFragment;
import com.example.ahmed.ibake.Recipes.RecipeData;
import com.example.ahmed.ibake.Recipes.Steps;

public class RecipeDetailsActivity extends AppCompatActivity {

    private static final String DETAILS_FRAGMENT = "detailsFragment";
    private static final String TAG_STEP_FRAGMENT = "stepFragment";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //Do this for details fragment
        RecipeDetailsFragment recipeDetailsFragment = (RecipeDetailsFragment) getSupportFragmentManager().
                findFragmentByTag(DETAILS_FRAGMENT);

        if(recipeDetailsFragment == null) {

            recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setRecipeDataObj((RecipeData) getIntent().getSerializableExtra
                    (RecipeDetailsFragment.KEY_RECIPE_DATA_OBJ));
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_recipe_details, recipeDetailsFragment, DETAILS_FRAGMENT);
            transaction.commit();
        }

        recipeDetailsFragment.setAction(actionListener);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(findViewById(R.id.fl_tab_recipe_step) != null) {

            StepFragment stepFragment = (StepFragment) getSupportFragmentManager()
                    .findFragmentByTag(TAG_STEP_FRAGMENT);

            if(stepFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(stepFragment);
                transaction.commitAllowingStateLoss();
            }
        }
    }

    private RecipeDetailsFragment.IStepAction actionListener = new RecipeDetailsFragment.IStepAction() {
        @Override
        public void onStepClicked(int position, Steps step) {

            if(findViewById(R.id.fl_tab_recipe_step) == null) {
                Intent intent = new Intent(getApplication(), StepActivity.class);
                intent.putExtra(StepFragment.KEY_STEP_OBJECT, step);
                startActivity(intent);

            } else {
                setupSetFragmentForTablet(step);
            }
        }
    };

    private void setupSetFragmentForTablet(Steps step) {

        StepFragment stepFragment = (StepFragment) getSupportFragmentManager()
                .findFragmentByTag(TAG_STEP_FRAGMENT);

        if(stepFragment == null) {

            stepFragment = new StepFragment();
            stepFragment.setStep(step);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fl_tab_recipe_step, stepFragment, TAG_STEP_FRAGMENT);
            fragmentTransaction.commit();
        } else {
            stepFragment.updateStep(step);
        }
    }
}
