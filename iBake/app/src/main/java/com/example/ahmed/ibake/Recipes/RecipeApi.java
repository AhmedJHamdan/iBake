package com.example.ahmed.ibake.Recipes;

import java.util.LinkedList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RecipeApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    @Streaming
    Call<LinkedList<RecipeData>> downloadRecipe();
}
