package com.example.ahmed.ibake.RecipeDetails;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ahmed.ibake.Recipes.Ingredients;
import com.example.ahmed.ibake.R;
import com.example.ahmed.ibake.databinding.RowRecipeIngredientBinding;

import java.util.LinkedList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private LinkedList<Ingredients> ingredientsList;

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RowRecipeIngredientBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.row_recipe_ingredient,
                parent, false);

        return new IngredientViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return null == ingredientsList ? 0 : ingredientsList.size();
    }

    public void setIngredientsList(LinkedList<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
        notifyDataSetChanged();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {

        private RowRecipeIngredientBinding binding;

        public IngredientViewHolder(RowRecipeIngredientBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(int position) {

            Ingredients ingredients = ingredientsList.get(position);

            binding.tvIngreDetails.setText(ingredients.getQuantity() + " " +
                    ingredients.getMeasure() + " " +
                    ingredients.getIngredient());
        }
    }
}
