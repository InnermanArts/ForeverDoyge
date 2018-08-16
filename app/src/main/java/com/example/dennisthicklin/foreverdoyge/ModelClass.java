package com.example.dennisthicklin.foreverdoyge;

/**
 * Created by dennisthicklin on 10/29/17.
 */

public class ModelClass {
    String image;
    String productDescription;

    public ModelClass(String image){
        this.image = image;
        this.productDescription = productDescription;
    }

    public ModelClass(){

    }

    public String getImage(){
        return image;
    }
    public String getProductDescription(){return productDescription;}

    public void setImage(String image){
        this.image = image;
    }
}
