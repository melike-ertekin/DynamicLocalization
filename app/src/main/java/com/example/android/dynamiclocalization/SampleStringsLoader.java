package com.example.android.dynamiclocalization;

import android.util.Log;

import com.example.android.dynamiclocalization.api.GetLanguageService;
import com.example.android.dynamiclocalization.api.RetrofitInstance;
import com.ice.restring.Restring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is just a really simple sample of strings loader.
 * in real applications, you might call an API to get your strings.
 * <p>
 * All overridden methods will be called on background thread.
 */
public class SampleStringsLoader implements Restring.StringsLoader {




    @Override
    public List<String> getLanguages() {
        return new ArrayList<String>(SplashScreenActivity.getDictionary().keySet());
    }

    @Override
    public Map<String, String> getStrings(String language) {

        Map<String, String> map = new HashMap<>();
        map = SplashScreenActivity.getDictionary().get(language);
        return map;
    }





}
