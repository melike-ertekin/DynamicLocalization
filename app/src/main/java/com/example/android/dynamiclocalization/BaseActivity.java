package com.example.android.dynamiclocalization;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ice.restring.Restring;

import java.util.Locale;

/**
 * We should wrap the base context of our activities, which is better to put it
 * on BaseActivity, so that we don't have to repeat it for all activities one-by-one.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Restring.wrapContext(newBase));
    }


}
