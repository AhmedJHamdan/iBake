package com.example.ahmed.ibake.RecipeDetails;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmed.ibake.R;
import com.example.ahmed.ibake.Recipes.Steps;
import com.example.ahmed.ibake.databinding.RowRecipeStepsBinding;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {

    public interface IStepAction {
        void onStepClicked(int position);
    }

    private LinkedList<Steps> stepsList;
    private IStepAction action;

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RowRecipeStepsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.row_recipe_steps,
                parent, false);

        return new StepsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(StepsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return null == stepsList ? 0 : stepsList.size();
    }

    public void setStepsList(LinkedList<Steps> stepsList) {
        this.stepsList = stepsList;
        notifyDataSetChanged();
    }

    public void setClickListener(IStepAction action) {
        this.action = action;
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder {

        private RowRecipeStepsBinding binding;

        public StepsViewHolder(RowRecipeStepsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(listener);
        }

        public void bind(int position) {

            String thumbnailUrl = stepsList.get(position).getThumbnailUrl();

            if(null != thumbnailUrl && !(TextUtils.isEmpty(thumbnailUrl))) {

                Picasso.get()
                        .load(thumbnailUrl)
                        .placeholder(R.drawable.default_step)
                        .into(binding.ivStepThumbnail);
            }

            binding.tvShortDescription.setText(stepsList.get(position).getShortDescription());
        }

        private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action.onStepClicked(getAdapterPosition());
            }
        };
    }
}
