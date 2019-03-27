package com.example.android.dynamiclocalization.api;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetLanguageService {

    //String ALL_LANGUAGES_REQUEST = "lang_types";

    String ALL_STRINGS_REQUEST = "{lang}";

    @GET(ALL_STRINGS_REQUEST)
    Call<Map<String, String>> getStrings(@Path(value = "lang", encoded = true) String lang);

   // @GET(ALL_LANGUAGES_REQUEST)
    //Call<ArrayList<String>> getLanguages();
}
