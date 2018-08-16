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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

public class SkirtProduct extends AppCompatActivity {

    Spinner sizeSpinner;
    ImageView product;
    DrawerLayout myDrawerLayout;
    NavigationView myNavigationView;
    ImageButton homeButton;
    Button sideBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skirt_product);


        sizeSpinner = findViewById(R.id.sizeSpinner);
        product = findViewById(R.id.productImage);
        myDrawerLayout = findViewById(R.id.drawer_layout);
        myNavigationView = findViewById(R.id.navigationView);
        homeButton = findViewById(R.id.homeButton);
        sideBarMenu = findViewById(R.id.list);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SkirtProduct.this, MainActivity.class);
                startActivity(i);
            }
        });

        sideBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDrawerLayout.openDrawer(Gravity.START);
            }
        });

        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Intent intent = new Intent(SkirtProduct.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.open_to_individual:
                        Intent x = new Intent(SkirtProduct.this, OpenToIndividual.class);
                        startActivity(x);
                        break;
                    case R.id.contact:
                        Intent i = new Intent(SkirtProduct.this, Contact.class);
                        startActivity(i);
                        break;
                }

                return false;
            }
        });

        Intent i = this.getIntent();
        String image = i.getExtras().getString("image");
        Picasso.with(SkirtProduct.this).load(image).into(product);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.skirtsizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);
    }
}
