package com.example.getimei;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imei = "empty";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkPerm();
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

//        if (telephonyManager!=null){
////            imei=telephonyManager.getDeviceId();
//            Log.i("tagIMEI",imei);
//        }



//        getIMEI2(telephonyManager);

        // OR

        // this code works better
        getIMEI();
        Log.i("tagIMEI", imei);

    }

    private void getIMEI() {
        imei = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    private void getIMEI2(TelephonyManager telephonyManager){
        if (Build.VERSION.SDK_INT >= 26) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            imei = telephonyManager.getImei();
        }
    }

    private void checkPerm() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "permOk", Toast.LENGTH_SHORT).show();
        }
    }
}
