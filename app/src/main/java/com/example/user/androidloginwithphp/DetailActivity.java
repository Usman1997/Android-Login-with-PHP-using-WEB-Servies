package com.example.user.androidloginwithphp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.androidloginwithphp.model.ListModel;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;

/**
 * Created by User on 10/21/2017.
 */

public class DetailActivity extends AppCompatActivity {
    TextView title,price,desc;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView)findViewById(R.id.title);
        desc = (TextView)findViewById(R.id.description);
        price = (TextView)findViewById(R.id.price);
        imageView = (ImageView)findViewById(R.id.image);

        ListModel listModel = (ListModel) getIntent().getSerializableExtra("product");

        title.setText(listModel.title);
        desc.setText(listModel.description);
        price.setText(String.valueOf(listModel.price));
//        Picasso.with(DetailActivity.this).load(listModel.image_url).into(imageView);
        Bitmap bitmap = null;
        try {
            bitmap = ImageLoader.init().from(listModel.image_url).requestSize(120,80).getBitmap();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
    }
}
