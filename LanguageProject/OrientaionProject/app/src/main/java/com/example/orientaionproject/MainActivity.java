package com.example.orientaionproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // if we set the orientation the life cycle will not happen
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation== Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
        }else if (orientation==Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
        }
    }
}
