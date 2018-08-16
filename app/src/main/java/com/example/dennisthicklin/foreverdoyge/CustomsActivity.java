package com.example.dennisthicklin.foreverdoyge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class CustomsActivity extends AppCompatActivity {

    ViewPager imageSlider;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Button sideBarMenu;
    ImageButton homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customs);

        imageSlider = findViewById(R.id.imageSlider);
        homeButton = findViewById(R.id.homeButton);
        navigationView =  findViewById(R.id.navigationView);
        sideBarMenu = findViewById(R.id.list);


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Intent i = new Intent(CustomsActivity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.open_to_individual:
                        Intent intent = new Intent(CustomsActivity.this, OpenToIndividual.class);
                        startActivity(intent);
                        break;
                    case R.id.contact:
                        Intent ix = new Intent(CustomsActivity.this, Contact.class);
                        startActivity(ix);
                        break;
                }

                return false;
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);



        sideBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.START);
            }
        });

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(this);

        imageSlider.setAdapter(imageSliderAdapter);

        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(), 2000, 4000);
    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            CustomsActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (imageSlider.getCurrentItem() == 0) {
                        imageSlider.setCurrentItem(1);
                    } else if (imageSlider.getCurrentItem() == 1) {
                        imageSlider.setCurrentItem(2);
                    } else {
                        imageSlider.setCurrentItem(0);
                    }

                }
            });
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
}
