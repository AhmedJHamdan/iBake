package com.example.ahmed.ibake.Network;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class        RetrofitProvider {

    private static RetrofitProvider instance;
    private Map<String, Retrofit> retrofitMap;

    /**
     * Provides the singleton instance of this class
     * @return
     */
    public static RetrofitProvider getInstance() {

        if(null == instance) {
            instance = new RetrofitProvider();
        }

        return instance;
    }

    /**
     * Returns the retrofit object associated uniquely with the provided baseUrl.
     * @param baseUrl
     * @return
     */
    public Retrofit getRetrofit(String baseUrl) {

        return retrofitMap.containsKey(baseUrl) ? retrofitMap.get(baseUrl) : createRetrofit(baseUrl);
    }

    private RetrofitProvider() {
        retrofitMap = new HashMap<>();
    }

    /*
     * Creates an instance of the retrofit object and keeps it in the map.
     * This would ensure that we have unique retrofit object per unique
     * base URL.
     */
    private Retrofit createRetrofit(String baseUrl) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitMap.put(baseUrl, retrofit);

        return retrofit;
    }
}
