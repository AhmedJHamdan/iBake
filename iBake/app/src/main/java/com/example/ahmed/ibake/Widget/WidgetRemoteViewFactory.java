package com.example.ahmed.ibake.Widget;


import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.ahmed.ibake.BakeWidget;
import com.example.ahmed.ibake.R;
import com.example.ahmed.ibake.Recipes.RecipeData;

public class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private RecipeData recipeData;
    private Context context;

    public WidgetRemoteViewFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        recipeData = BakeWidget.storedRecipeData;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return recipeData.getIngredients().size();
    }

    @Override
    public RemoteViews getViewAt(int i) {

        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_row_ingredient);
        rv.setTextViewText(R.id.tv_widget_ingre_name, recipeData.getIngredients().get(i).getIngredient());

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
