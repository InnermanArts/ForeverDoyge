package com.example.dennisthicklin.foreverdoyge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class LuxeProductsScreen extends AppCompatActivity {

    private RecyclerView productsList;
    FirebaseDatabase database;
    DatabaseReference myDatabaseReference;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Button sideBarMenu;
    ImageButton homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_screen);

        productsList =  findViewById(R.id.productlist);
        productsList.setLayoutManager(new LinearLayoutManager(this));
        productsList.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance();
        myDatabaseReference = database.getReference("Luxe");

        navigationView =  findViewById(R.id.navigationView);
        sideBarMenu = findViewById(R.id.list);
        homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LuxeProductsScreen.this, MainActivity.class);
                startActivity(i);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Intent i = new Intent(LuxeProductsScreen.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.open_to_individual:
                        Intent intent = new Intent(LuxeProductsScreen.this, OpenToIndividual.class);
                        startActivity(intent);
                        break;
                    case R.id.contact:
                        Intent ix = new Intent(LuxeProductsScreen.this, Contact.class);
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
    }




    @Override
    protected void onStart() {
        super.onStart();

       FirebaseRecyclerAdapter<ModelClass, ProductViewHolder> firebaseRecyclerAdapter =
               new FirebaseRecyclerAdapter<ModelClass, ProductViewHolder>(
                    ModelClass.class,
                    R.layout.design_row,
                    ProductViewHolder.class,
                    myDatabaseReference) {
           @Override
           protected void populateViewHolder(ProductViewHolder viewHolder, final ModelClass model, int position){
               viewHolder.setImage(getApplicationContext(), model.getImage());
               viewHolder.myView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(model.getImage().contains("skirt")){
                           Intent intent = new Intent(LuxeProductsScreen.this, SkirtProduct.class);
                           intent.putExtra("image", model.getImage());
                           startActivity(intent);
                       }else if(model.getImage().contains("dashiki")){
                           Intent intent = new Intent(LuxeProductsScreen.this, DashikiProduct.class);
                           intent.putExtra("image", model.getImage());
                           startActivity(intent);
                       }else{
                           Intent intent = new Intent(LuxeProductsScreen.this, ProductScreen.class);
                           intent.putExtra("image", model.getImage());
                           startActivity(intent);
                       }
                   }
               });
            }
        };

            productsList.setAdapter(firebaseRecyclerAdapter);
        }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        View myView;
        public ProductViewHolder(View productView){
            super(productView);
            myView = productView;
        }
        public void setImage(Context ctx, String image) {
            ImageView productImageButton = myView.findViewById(R.id.productImageButton);
            Picasso.with(ctx).load(image).into(productImageButton);
        }


    }
}

