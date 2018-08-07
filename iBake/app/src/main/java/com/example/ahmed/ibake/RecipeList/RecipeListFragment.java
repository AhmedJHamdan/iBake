package com.example.ahmed.ibake.RecipeList;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmed.ibake.R;
import com.example.ahmed.ibake.Recipes.RecipeData;
import com.example.ahmed.ibake.Recipes.RecipeLoader;
import com.example.ahmed.ibake.databinding.FragmentRecipeListBinding;

import java.util.LinkedList;

import icepick.Icepick;
import icepick.State;

public class RecipeListFragment extends Fragment implements LoaderManager.LoaderCallbacks<LinkedList<RecipeData>> {

    public interface IRecipeAction {
        void onRecipeClicked(RecipeData recipeData);
    }

    private static final int LOADER_RECIPE = 1001;

    @State
    int scrollPosition = 0;

    private RecyclerView recipeList;
    private RecipeListAdapter adapter;
    private IRecipeAction action;
    private FragmentRecipeListBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Icepick.restoreInstanceState(this, savedInstanceState);

        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_recipe_list, container, false);

        int spanCount = (getResources().getBoolean(R.bool.isTablet) ? 2 : 1);
        int orientation = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        switch(orientation) {
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                spanCount = (getResources().getBoolean(R.bool.isTablet) ? 3 : 2);
                break;
        }

        recipeList = binding.rvRecipeList;
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recipeList.setLayoutManager(layoutManager);
        adapter = new RecipeListAdapter(handleRecipeAction);
        recipeList.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = new Bundle();
        bundle.putInt("mode", RecipeLoader.MODE_LIST);
        getActivity().getSupportLoaderManager().restartLoader(LOADER_RECIPE, bundle, this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getSupportLoaderManager().destroyLoader(LOADER_RECIPE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        scrollPosition = ((GridLayoutManager)recipeList.getLayoutManager())
                .findFirstVisibleItemPosition();

        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public Loader<LinkedList<RecipeData>> onCreateLoader(int id, Bundle args) {

        return new RecipeLoader(getActivity(), RecipeLoader.MODE_LIST, LOADER_RECIPE);
    }

    @Override
    public void onLoadFinished(Loader<LinkedList<RecipeData>> loader, LinkedList<RecipeData> data) {

        binding.pbProgress.setVisibility(View.GONE);
        adapter.setRecipeDataList(data);

        recipeList.getLayoutManager().scrollToPosition(scrollPosition);
    }

    @Override
    public void onLoaderReset(Loader<LinkedList<RecipeData>> loader) {
        //
    }

    public void setIRecipeAction(IRecipeAction action) {
        this.action = action;
    }

    private RecipeListAdapter.IRecipeAction handleRecipeAction = new RecipeListAdapter.IRecipeAction() {

        @Override
        public void onRecipeClicked(int position, RecipeData recipeData) {

            if(null != action) {
                action.onRecipeClicked(recipeData);
            }
        }
    };
}
