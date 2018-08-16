package com.example.dennisthicklin.foreverdoyge;

import android.content.Intent;
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

public class OpenToIndividual extends AppCompatActivity {

    Button sideBarMenuButton;
    private DrawerLayout mDrawerLayout;
    NavigationView myNavView;
    ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_to_individual);

        homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OpenToIndividual.this, MainActivity.class);
                startActivity(i);
            }
        });
        sideBarMenuButton = (Button) findViewById(R.id.list);
        myNavView = (NavigationView) findViewById(R.id.navigationView);

        myNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(OpenToIndividual.this, MainActivity.class);
                        startActivity(intent);
                            break;
                    case R.id.contact:
                        Intent x = new Intent(OpenToIndividual.this, Contact.class);
                        startActivity(x);
                            break;
                    case R.id.open_to_individual:
                        mDrawerLayout.closeDrawer(Gravity.START);
                        break;
                }
                return false;
            }

        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        sideBarMenuButton.setOnClickListener(new View.OnClickListener() {
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
