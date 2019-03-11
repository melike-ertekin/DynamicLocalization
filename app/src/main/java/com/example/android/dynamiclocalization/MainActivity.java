package com.example.android.dynamiclocalization;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
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

import com.ice.restring.Restring;
import com.ice.restring.RestringConfig;

import java.util.Locale;
import java.util.Map;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();

        if (menuItem == R.id.en) {
            getSupportActionBar().setTitle("EN");
            Locale.setDefault(new Locale("en"));

        } else if (menuItem == R.id.tr) {
            getSupportActionBar().setTitle("TR");
            Locale.setDefault(new Locale("tr"));

        }
        return super.onOptionsItemSelected(item);
    }





}
