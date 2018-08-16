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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductScreen extends AppCompatActivity {

    Spinner sizeSpinner;
    ImageView productImage;
    TextView productDescription;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DrawerLayout myDrawerLayout;
    NavigationView navigationMenu;
    ImageButton homeButton;
    Button sideBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_screen);

        sideBarMenu = findViewById(R.id.list);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        productImage = findViewById(R.id.productImage);
        myDrawerLayout = findViewById(R.id.drawer_layout);
        navigationMenu = findViewById(R.id.navigationView);
        homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductScreen.this, MainActivity.class);
                startActivity(i);
            }
        });

        sideBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDrawerLayout.openDrawer(Gravity.START);
            }
        });

        navigationMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean  onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Intent x = new Intent(ProductScreen.this, MainActivity.class);
                        startActivity(x);
                        break;
                    case R.id.open_to_individual:
                        Intent intent = new Intent(ProductScreen.this, OpenToIndividual.class);
                        startActivity(intent);
                        break;
                    case R.id.contact:
                        Intent i = new Intent(ProductScreen.this, Contact.class);
                        startActivity(i);
                        break;
                }

                return false;
            }
        });

        productDescription = findViewById(R.id.productDescription);
        database = FirebaseDatabase.getInstance("https://foreverdoyge-70bc5.firebaseio.com/");
        myRef = database.getReference("description");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String description = dataSnapshot.getValue(String.class);
                productDescription.setText(description);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sizeSpinner.setAdapter(adapter);

        Intent i = this.getIntent();
        String image = i.getExtras().getString("image");
        Picasso.with(ProductScreen.this).load(image).into(productImage);


    }
}
