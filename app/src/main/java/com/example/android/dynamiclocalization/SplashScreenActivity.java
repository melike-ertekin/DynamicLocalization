package com.example.android.dynamiclocalization;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.android.dynamiclocalization.api.GetLanguageService;
import com.example.android.dynamiclocalization.api.RetrofitInstance;
import com.ice.restring.Restring;
import com.ice.restring.RestringConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {


    private static Map<String, String> langBasedMap;
    private static List<String> languages = new ArrayList<>();

    private SharedPreferences sharedPreferences;
    long currentTime =Calendar.getInstance().getTimeInMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE);
        String currentLang = Locale.getDefault().getLanguage();


        //long duration = TimeUnit.HOURS.toMillis(1);
        //FOR TESTING 1 MINUTE
        long duration = TimeUnit.MINUTES.toMillis(1);
        long savedTime = sharedPreferences.getLong("last_update_time", -1);
        String savedLang = sharedPreferences.getString("current_lang", "en");

        //call api to get strings and update time in bundle
        if (isNetworkConnected() && (savedTime == -1 || currentTime - savedTime > duration || !savedLang.equals(currentLang))) {
            int minutes = (int)TimeUnit.MILLISECONDS.toMinutes(currentTime - savedTime);
            if(savedTime == -1){
                minutes =0;
            }
            //First time downloading app or change language of device
            if (savedTime == -1 || !savedLang.equals(currentLang)) {

                savedLang = saveCurrentLang(currentLang);
                Toast.makeText(SplashScreenActivity.this, minutes + "min . First time running app or current language is changed(API CALL)", Toast.LENGTH_LONG).show();
            }
            //Time is expired. Call api to get updates.
            else {
                Toast.makeText(SplashScreenActivity.this, minutes + "min. Time is expired.(API CALL) ", Toast.LENGTH_LONG).show();

            }
            languages.add(savedLang);
            getStringsFromAPI(savedLang);
        }
        //No api call!!
        else {

            if(!isNetworkConnected()){
                Toast.makeText(SplashScreenActivity.this, "No internet!No api call!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(SplashScreenActivity.this, "No api call!", Toast.LENGTH_SHORT).show();
            }

            languages.add(savedLang);
            changeActivity();
        }
    }

    public void getStringsFromAPI(final String lang) {

        /*Create handle for the RetrofitInstance interface*/
        GetLanguageService all_service = RetrofitInstance.getRetrofitInstance().create(GetLanguageService.class);

        Call<Map<String, String>> call = all_service.getStrings(lang);

            call.enqueue(new Callback<Map<String, String>>() {


                @Override
                public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                    if (response.isSuccessful()) {
                        langBasedMap = response.body();
                    }else{

                        saveCurrentLang("en");
                        languages.clear();
                        languages.add("en");
                    }
                    saveLastUpdateTime();
                    changeActivity();
                }

                @Override
                public void onFailure(Call<Map<String, String>> call, Throwable t) {
                    showErrorMessage(t);
                }
            });
        }


    public void showErrorMessage(Throwable t) {
        Toast.makeText(SplashScreenActivity.this, t.toString(), Toast.LENGTH_LONG).show();
    }

    public void changeActivity() {
        CreateReString();
        CallMainActivity();
    }

    private void CreateReString() {
        Restring.init(SplashScreenActivity.this,
                new RestringConfig.Builder()
                        .persist(true)
                        .stringsLoader(new SampleStringsLoader())
                        .build()
        );
    }

    private void CallMainActivity() {
        Intent intent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    private String saveCurrentLang(String currentLang) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = sharedPreferences.edit();
        editor.putString("current_lang", currentLang);
        editor.commit();
        return currentLang;
    }

    private void saveLastUpdateTime() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor = sharedPreferences.edit();
        editor.putLong("last_update_time", currentTime);
        editor.commit();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Map<String, String> getLangBasedMap() {
        return langBasedMap;
    }

    public static List<String> getLanguages() {
        return languages;
    }

    public static void setLangBasedMap(Map<String, String> langBasedMap) {
        SplashScreenActivity.langBasedMap = langBasedMap;
    }
}
