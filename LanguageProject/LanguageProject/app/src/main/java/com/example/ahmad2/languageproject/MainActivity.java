package com.example.ahmad2.languageproject;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String currentLang;

    @Override
    protected void attachBaseContext(Context newBase) {
        preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        currentLang = preferences.getString("LANG", "fa");
        Context context = changeLanguage(newBase, currentLang);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String appLanguage = getAppLanguage();
        Toast.makeText(this, appLanguage, Toast.LENGTH_SHORT).show();
        Button btChangeLang = findViewById(R.id.btChangeLang);
        btChangeLang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (currentLang.equals("fa")) {
                    currentLang = "en";
                } else {
                    currentLang = "fa";
                }
                preferences.edit().putString("LANG", currentLang).apply();
                recreate();
            }
        });
    }

    public static ContextWrapper changeLanguage(Context context, String lang) {
        Locale currentLocal;
        Resources res  = context.getResources();
        Configuration conf = res.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            currentLocal = conf.getLocales().get(0);
        } else {
            currentLocal = conf.locale;
        }

        if (!lang.equals("") && !currentLocal.getLanguage().equals(lang)) {
            Locale newLocal = new Locale(lang);
            Locale.setDefault(newLocal);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                conf.setLocale(newLocal);
            } else {
                conf.locale = newLocal;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(conf);
            } else {
                res.updateConfiguration(conf, context.getResources().getDisplayMetrics());
            }
        }

        return new ContextWrapper(context);
    }


    public static String getAppLanguage() {
        return Locale.getDefault().getLanguage();
    }

}
