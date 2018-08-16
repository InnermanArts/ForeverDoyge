package com.example.dennisthicklin.foreverdoyge;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    Button tshirtButton;
    Button luxButton;
    Button vintageButton;
    Button fdy2Button;
    Button youthButton;
    Button sideBarMenu;
    ImageButton homeButton;
    Button customsButton;
    ImageView barheader;
    RelativeLayout header;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView =  findViewById(R.id.navigationView);
        youthButton =  findViewById(R.id.youth_button);
        customsButton =  findViewById(R.id.customs_button);
        tshirtButton =  findViewById(R.id.tshirt_button);
        luxButton =  findViewById(R.id.lux_button);
        vintageButton =  findViewById(R.id.vintage_button);
        fdy2Button =  findViewById(R.id.fdy2_button);
        sideBarMenu =  findViewById(R.id.list);
        barheader = findViewById(R.id.bar);
        homeButton = findViewById(R.id.homeButton);


        tshirtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ConsciousTeesScreen.class);
                startActivity(i);
            }
        });

        luxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LuxeProductsScreen.class);
                startActivity(i);
            }
        });

        vintageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, VintageProductsScreen.class);
                startActivity(i);
            }
        });

        fdy2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FDY2ProductsScreen.class);
                startActivity(i);
            }
        });

        youthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, YouthProductsScreen.class);
                startActivity(i);
            }
        });

        customsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CustomsActivity.class);
                startActivity(i);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        mDrawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.open_to_individual:
                        Intent intent = new Intent(MainActivity.this, OpenToIndividual.class);
                        startActivity(intent);
                        break;
                    case R.id.contact:
                        Intent i = new Intent(MainActivity.this, Contact.class);
                        startActivity(i);
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
