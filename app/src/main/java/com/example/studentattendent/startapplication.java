package com.example.studentattendent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class startapplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapplication);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getBaseContext(),login.class);
                startActivity(i);
                finish();
            }
        };new Handler().postDelayed(runnable,3500);
    }
}
