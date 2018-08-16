package com.example.dennisthicklin.foreverdoyge;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.EditText;
import android.widget.ImageButton;

public class Contact extends AppCompatActivity {

    Button drawerButton;
    Button cart;
    ImageButton homeButton;
    Button submitButton;
    EditText name_field, email_field, subject_field, message_field;
    ImageButton instaButton, facebookButton;
    DrawerLayout myDrawerLayout;
    NavigationView myNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        subject_field = findViewById(R.id.subject_field);
        name_field = findViewById(R.id.name_field);
        message_field = findViewById(R.id.message_field);
        email_field = findViewById(R.id.email_text_field);
        homeButton = findViewById(R.id.homeButton);
        instaButton = findViewById(R.id.insta_button);
        facebookButton = findViewById(R.id.facebook_button);
        submitButton = findViewById(R.id.submitButton);
        drawerButton = (Button) findViewById(R.id.list);
        myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDrawerLayout.openDrawer(Gravity.START);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Contact.this, MainActivity.class);
                startActivity(i);
            }
        });

        instaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWebsite("https://www.instagram.com/foreverdoyge/");
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWebsite("https://www.facebook.com/Foreverdoyge/?hc_ref=ARQPHWrZ0iwZZPjjA1snKLT5A-EpHBNE_5ZYUBLTW1c2OhKJGxOMdsx0mNKL1ej0OpA&fref=nf");

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent sendEmail = new Intent(Intent.ACTION_SEND);
               sendEmail.setData(Uri.parse("mailto:"));
               String[] emailTo = {"ForeverDoyge@gmail.com"};
               sendEmail.putExtra(Intent.EXTRA_EMAIL, emailTo);
               sendEmail.putExtra(Intent.EXTRA_SUBJECT,  name_field.getText().toString() + " : " + subject_field.getText().toString());
               sendEmail.putExtra(Intent.EXTRA_TEXT, message_field.getText().toString());
               sendEmail.setType("message/rfc822");
               startActivity(Intent.createChooser(sendEmail, "Send Us Your Message"));
            }
        });


        myNavigationView = (NavigationView) findViewById(R.id.navigationView);
        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent i = new Intent(Contact.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.open_to_individual:
                        Intent x = new Intent(Contact.this, OpenToIndividual.class);
                        startActivity(x);
                        break;
                    case R.id.contact:
                        myDrawerLayout.closeDrawer(Gravity.START);
                        break;
                }

                return false;
            }
        });

    }

    private void goToWebsite(String url) {
        Uri myUri = Uri.parse(url);
        Intent goToBrowser = new Intent(Intent.ACTION_VIEW, myUri);
        startActivity(goToBrowser);
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

