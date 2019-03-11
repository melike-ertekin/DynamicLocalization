package com.example.android.dynamiclocalization.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetLanguageService {


    String ALL_STRINGS_REQUEST = "result";

    @GET(ALL_STRINGS_REQUEST)
    Call<Map<String, Map<String, String>>> getAllData();
}
