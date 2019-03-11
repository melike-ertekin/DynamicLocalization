package com.example.android.dynamiclocalization.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link RetrofitInstance} is a singleton class for the Retrofit.
 */
public class RetrofitInstance {


    private static final String BASE_URL = "https://my-json-server.typicode.com/melike-ertekin/testJSONDataDynamicLocalization/master/db.json/";
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}