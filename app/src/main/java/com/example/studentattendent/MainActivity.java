package com.example.studentattendent;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    String myear,mday;
    String insertday;
    SessionManager sessionManager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();
                    return true;
                case R.id.timetable:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new TimeTable()).commit();
                    return true;
                case R.id.checknames:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new checkname()).commit();
                    return true;
                case R.id.transcript:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new transcript()).commit();
                    return true;
                case R.id.gps:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new maps()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();


        Calendar c = Calendar.getInstance();
        SimpleDateFormat year = new SimpleDateFormat("dd/MM");
        myear = year.format(c.getTime());
        int d = c.get(Calendar.YEAR)+543;
        mday = new SimpleDateFormat("EEEE").format(c.getTime());
        sessionManager = new SessionManager(this);
        sessionManager.datenow(mday,myear);
//
//        datenow.setText(myear+"/"+d);
//        daynow.setText(mday);
//        insertday = daynow.getText().toString().trim();
//        Toast.makeText(getActivity(), insertday, Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemtop) {
        int id = itemtop.getItemId();
        switch (id) {
            case R.id.qrcode_menu:
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new qrcode()).commit();
                return true;
            case R.id.logout_menu:
                startActivity(new Intent(MainActivity.this,login.class));
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }

}
