package com.example.android.dynamiclocalization;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.dynamiclocalization.api.GetLanguageService;
import com.example.android.dynamiclocalization.api.RetrofitInstance;
import com.ice.restring.Restring;

import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    public static Map<String, Map<String, String>> dictionary;
    public static Map<String, String> langBasedMap;

    public static void setDictionaryBasedOnLanguages(String key) {
        langBasedMap = dictionary.get(key);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getAllData();

    }

    private void getAllData() {

        /*Create handle for the RetrofitInstance interface*/
        GetLanguageService all_service = RetrofitInstance.getRetrofitInstance().create(GetLanguageService.class);


        Call<Map<String, Map<String, String>>> call = all_service.getAllData();

        call.enqueue(new Callback<Map<String, Map<String, String>>>() {
            @Override
            public void onResponse(Call<Map<String, Map<String, String>>> call, Response<Map<String, Map<String, String>>> response) {
                if (response.isSuccessful()) {
                    dictionary = response.body();
                    String currentLang = Locale.getDefault().getLanguage();
                    setDictionaryBasedOnLanguages(currentLang);

                    //change the ReString strings
                    for(Map.Entry<String, Map<String, String>> current: dictionary.entrySet()) {
                        Restring.setStrings(current.getKey(), current.getValue());
                    }

                    Intent intent = new Intent(getApplicationContext(),
                            MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<Map<String, Map<String, String>>> call, Throwable t) {
                showErrorMessage();
            }


        });
    }

    public void showErrorMessage() {

        Toast.makeText(SplashScreenActivity.this, "Network Issue", Toast.LENGTH_LONG).show();

    }
}
