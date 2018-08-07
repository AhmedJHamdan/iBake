package com.example.ahmed.ibake.RecipeList;


import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmed.ibake.R;
import com.example.ahmed.ibake.Recipes.RecipeData;
import com.example.ahmed.ibake.databinding.RecipeCardBinding;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    public interface IRecipeAction {
        void onRecipeClicked(int position, RecipeData recipeData);
    }

    private LinkedList<RecipeData> recipeList;
    private IRecipeAction action;

    public RecipeListAdapter(@NonNull IRecipeAction action) {

        this.action = action;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecipeCardBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.recipe_card,
                parent, false);

        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return null == recipeList ? 0 : recipeList.size();
    }

    public void setRecipeDataList(LinkedList<RecipeData> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        private RecipeCardBinding binding;

        public RecipeViewHolder(RecipeCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(listener);
        }

        public void bind(int position) {

            //If image path is mentioned, then load using Picasso
            String imageData = recipeList.get(position).getImage();
            if(null != imageData && !TextUtils.isEmpty(imageData)) {
                Picasso.get()
                        .load(imageData)
                        .error(R.drawable.food)
                        .placeholder(R.drawable.food)
                        .into(binding.ivRecipeImage);
            }

            binding.tvRecipeName.setText(recipeList.get(position).getName());
            binding.tvRecipeServing.setText(Integer.toString(recipeList.get(position).getServings()));
        }

        private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(null != action) {
                    action.onRecipeClicked(getAdapterPosition(),
                            recipeList.get(getAdapterPosition()));
                }
            }
        };
    }
}
