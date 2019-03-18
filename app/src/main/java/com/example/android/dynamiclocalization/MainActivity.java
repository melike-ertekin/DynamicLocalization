package com.example.android.dynamiclocalization;

import android.content.Context;
import android.content.Intent;
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

    private static final int MENU_ITEM_ITEM1 = 1;

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
        SampleStringsLoader s1 = new SampleStringsLoader();
        for(int i=0; i<s1.getLanguages().size(); i++){
            menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, s1.getLanguages().get(i));
        }

        return super.onPrepareOptionsMenu(menu);
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_ITEM1:
                getSupportActionBar().setTitle(item.getTitle().toString());
                Locale.setDefault(new Locale(item.getTitle().toString()));
                updateUI(item.getTitle().toString());
                return true;

            default:
               return super.onOptionsItemSelected(item);

        }


    }


    public void updateUI(String newLang){

        Locale locale = new Locale(newLang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;

        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        this.setContentView(R.layout.activity_main);
    }

}
