package com.example.ahmed.ibake.RecipeDetails;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmed.ibake.R;
import com.example.ahmed.ibake.Recipes.RecipeData;
import com.example.ahmed.ibake.Recipes.Steps;
import com.example.ahmed.ibake.databinding.FragmentRecipeDetailsBinding;

import icepick.Icepick;
import icepick.State;

public class RecipeDetailsFragment extends Fragment {

    public interface IStepAction {
        void onStepClicked(int position, Steps step);
    }

    public static final String KEY_RECIPE_DATA_OBJ = "recipeDataObj";

    @State
    RecipeData recipeData;
    int scrollPositionX;
    int scrollPositionY;

    private FragmentRecipeDetailsBinding binding;
    private IngredientAdapter ingredientAdapter;
    private StepsAdapter stepsAdapter;
    private IStepAction action;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Icepick.restoreInstanceState(this, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_details, container, false);

        //Setup the recycler view
        binding.rvRecipeIngredients.setLayoutManager(new LinearLayoutManager(getActivity()));
        ingredientAdapter = new IngredientAdapter();
        binding.rvRecipeIngredients.setAdapter(ingredientAdapter);

        //Setup the recycler view
        int spanCount = (getResources().getBoolean(R.bool.isTablet) ? 2 : 1);
        int orientation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        switch(orientation) {
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                spanCount = (getResources().getBoolean(R.bool.isTablet) ? 1 : 2);
                break;
        }

        binding.rvRecipeSteps.setLayoutManager(new StaggeredGridLayoutManager(spanCount,
                StaggeredGridLayoutManager.VERTICAL));
        stepsAdapter = new StepsAdapter();
        stepsAdapter.setClickListener(stepClickListener);
        binding.rvRecipeSteps.setAdapter(stepsAdapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set the ingredients data
        ingredientAdapter.setIngredientsList(recipeData.getIngredients());

        //Set the steps data
        stepsAdapter.setStepsList(recipeData.getSteps());

        //Set the title
        if(null != binding.collapsingToolbar) {
            binding.collapsingToolbar.setTitle(recipeData.getName());
        }

        //Notify to update the widget
        sendIngredientBroadcast();

        binding.nsvContainer.scrollTo(scrollPositionX, scrollPositionY);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        scrollPositionX = binding.nsvContainer.getScrollX();
        scrollPositionY = binding.nsvContainer.getScrollY();

        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setRecipeDataObj(RecipeData recipeData) {
        this.recipeData = recipeData;
    }

    public void setAction(IStepAction action) {
        this.action = action;
    }

    private StepsAdapter.IStepAction stepClickListener = new StepsAdapter.IStepAction() {
        @Override
        public void onStepClicked(int position) {
            action.onStepClicked(position, recipeData.getSteps().get(position));
        }
    };

    private void sendIngredientBroadcast() {

        Intent ingreIntent = new Intent("com.example.ahmed.ibake.UPDATE_RECIPE_DATA");
        ingreIntent.putExtra("data", recipeData);
        getActivity().sendBroadcast(ingreIntent);
    }
}
