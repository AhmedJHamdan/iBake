package com.example.ahmed.ibake;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.ahmed.ibake.Recipes.RecipeData;
import com.example.ahmed.ibake.Widget.WidgetRemoteService;



public class BakeWidget extends AppWidgetProvider {

    public static RecipeData storedRecipeData;

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bake_widget);

        views.setTextViewText(R.id.tv_widget_recipe_name, storedRecipeData.getName());

        Intent ingreListIntent = new Intent(context, WidgetRemoteService.class);
        views.setRemoteAdapter(R.id.widget_grid_view, ingreListIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(
                new ComponentName(context, BakeWidget.class));

        for(int id : ids) {
            if(intent.getAction().contentEquals("com.example.ahmed.ibake.UPDATE_RECIPE_DATA")) {
                storedRecipeData = (RecipeData) intent.getSerializableExtra("data");
                updateAppWidget(context, AppWidgetManager.getInstance(context), id);
            }
        }

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

