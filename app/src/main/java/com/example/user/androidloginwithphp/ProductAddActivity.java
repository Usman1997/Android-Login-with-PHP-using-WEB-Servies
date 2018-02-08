package com.example.user.androidloginwithphp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by User on 10/21/2017.
 */

public class ProductAddActivity extends BaseActivity implements View.OnClickListener {

    EditText title,description,price;
    Button image,submit;
    String image_url;
    ImageView imageView;
    Bitmap bitmap;
    String encodedImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        title = (EditText)findViewById(R.id.title);
        description =(EditText)findViewById(R.id.description);
        price = (EditText)findViewById(R.id.price);
        imageView = (ImageView)findViewById(R.id.imageView);
        image = (Button)findViewById(R.id.image);
        submit = (Button)findViewById(R.id.submit);
        image.setOnClickListener(this);
        submit.setOnClickListener(this);



    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                image_url = cameraPhoto.getPhotoPath();
                try {
                    bitmap = ImageLoader.init().from(image_url).requestSize(120,80).getBitmap();
                    imageView.setImageBitmap(bitmap);
                    encodedImage = ImageBase64.encode(bitmap);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ProductAddActivity.this,"Error Loading Image",Toast.LENGTH_LONG).show();
                }
            }
            else if(requestCode == GALLERY_REQUST){
                Uri uri = data.getData();

                galleryPhoto.setPhotoUri(uri);
                image_url = galleryPhoto.getPath();


                try {
                    bitmap = ImageLoader.init().from(image_url).requestSize(120,80).getBitmap();
                    imageView.setImageBitmap(bitmap);
//                    encodedImage = ImageBase64.encode(bitmap);
//
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(ProductAddActivity.this,"Error Loading Image",Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                Dialog_image(ProductAddActivity.this);
                break;

            case R.id.submit:
                HashMap postdata = new HashMap();
                try {
                    bitmap = ImageLoader.init().from(image_url).requestSize(120,80).getBitmap();
                    encodedImage = ImageBase64.encode(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                postdata.put("title",title.getText().toString());
                postdata.put("description",description.getText().toString());
                postdata.put("price",price.getText().toString());
                postdata.put("mobile","android");
                postdata.put("image_url",encodedImage);
                PostResponseAsyncTask task = new PostResponseAsyncTask(this, postdata, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                               if(s.equals("success")){
                                   Toast.makeText(ProductAddActivity.this,"Product Inserted Succesfully",Toast.LENGTH_LONG).show();
                                   Intent i = new Intent(ProductAddActivity.this,ListActivity.class);
                                   startActivity(i);
                               }else{
                                   Toast.makeText(ProductAddActivity.this,"Product Not Added",Toast.LENGTH_LONG).show();
                               }
                    }
                });
                task.execute("http://192.168.64.50/AndroidLogin/insert_product.php");
                break;
        }
    }


}
