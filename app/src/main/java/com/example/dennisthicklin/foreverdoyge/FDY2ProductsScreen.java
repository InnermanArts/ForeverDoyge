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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

public class FDY2ProductsScreen extends AppCompatActivity {
    private RecyclerView productsList;
    private ImageLoader imageLoader;
    FirebaseDatabase database;
    DatabaseReference myDatabaseReference;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    Button sideBarMenu;
    ImageButton homeButton;
    DatabaseReference test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_screen);

        productsList =  findViewById(R.id.productlist);
        productsList.setLayoutManager(new LinearLayoutManager(this));
        productsList.setHasFixedSize(true);
        database = FirebaseDatabase.getInstance();
        myDatabaseReference = database.getReference("FDY2");
        homeButton = findViewById(R.id.homeButton);
        test = database.getReference("FDY2").child("fdy2(1)").child("description");



        navigationView =  findViewById(R.id.navigationView);
        sideBarMenu = findViewById(R.id.list);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FDY2ProductsScreen.this, MainActivity.class);
                startActivity(i);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        Intent i = new Intent(FDY2ProductsScreen.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.open_to_individual:
                        Intent intent = new Intent(FDY2ProductsScreen.this, OpenToIndividual.class);
                        startActivity(intent);
                        break;
                    case R.id.contact:
                        Intent ix = new Intent(FDY2ProductsScreen.this, Contact.class);
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


         class ProductViewHolder extends RecyclerView.ViewHolder{
            View myView;
            public ProductViewHolder(View productView){
                super(productView);
                myView = productView;
            }
            public void setImage(final Context ctx, String image, final AdapterView.OnItemClickListener listener, View productView){
                ImageView productImageButton =  myView.findViewById(R.id.productImageButton);
                Picasso.with(ctx).load(image).into(productImageButton);
            }
        }

        FirebaseRecyclerAdapter<ModelClass, LuxeProductsScreen.ProductViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelClass, LuxeProductsScreen.ProductViewHolder>(
                        ModelClass.class,
                        R.layout.design_row,
                        LuxeProductsScreen.ProductViewHolder.class,
                        myDatabaseReference) {
                    @Override
                    protected void populateViewHolder(final LuxeProductsScreen.ProductViewHolder viewHolder, final ModelClass model, int position){

                        viewHolder.setImage(getApplicationContext(), model.getImage());
                        viewHolder.myView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(model.getImage().contains("skirt")){
                                    Intent intent = new Intent(FDY2ProductsScreen.this, SkirtProduct.class);
                                    intent.putExtra("image", model.getImage());
                                    startActivity(intent);
                                }else if(model.getImage().contains("dashiki")){
                                    Intent intent = new Intent(FDY2ProductsScreen.this, DashikiProduct.class);
                                    intent.putExtra("image", model.getImage());
                                    startActivity(intent);
                                }else{
                                    Intent intent = new Intent(FDY2ProductsScreen.this, ProductScreen.class);
                                    intent.putExtra("image", model.getImage());
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                };

        productsList.setAdapter(firebaseRecyclerAdapter);
    }

}
