package com.example.user.androidloginwithphp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by User on 10/20/2017.
 */

public class ListModel implements Serializable{

//    public String title ="";
//    public String description = "";
//    public int image;
//
//
//    public ListModel(String title,String description,int image){
//        this.description = description;
//        this.image = image;
//        this.title = title;
//    }
//
//    public String getTitle(){
//        return title;
//    }
//    public String getDescription(){
//        return description;
//
//    }
//    public int getImage(){
//        return image;
//    }
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("price")
    public int price;

    @SerializedName("image_url")
    public String image_url;

}
