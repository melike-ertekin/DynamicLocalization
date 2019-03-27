package com.example.android.dynamiclocalization;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.dynamiclocalization.api.GetLanguageService;
import com.example.android.dynamiclocalization.api.RetrofitInstance;
import com.ice.restring.Restring;
import com.ice.restring.RestringConfig;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ((TextView) findViewById(R.id.phone_number)).setText(R.string.phone_number);
        ((TextView) findViewById(R.id.first_name)).setText(R.string.first_name);
        ((TextView) findViewById(R.id.last_name)).setText(R.string.last_name);
        ((Button) findViewById(R.id.save_button)).setText(R.string.save_button);
    }


}
