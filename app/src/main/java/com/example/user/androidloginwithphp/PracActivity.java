package com.example.user.androidloginwithphp;

         import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by User on 10/21/2017.
 */

public class PracActivity extends BaseActivity implements View.OnClickListener {


    EditText title,description,price;
    Button image,submit;
    String image_url;
    ImageView imageView;
    Bitmap bitmap;
    private int PICK_IMAGE_REQUEST = 1;
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

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK ){
            if (requestCode ==PICK_IMAGE_REQUEST && data != null && data.getData() != null) {
                Uri filePath = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    //bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    imageView.setImageBitmap(bitmap);
                    // imageView1.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    e.printStackTrace();

                    Toast.makeText(PracActivity.this,"Error Loading Image",Toast.LENGTH_LONG).show();
                }
            }

        }

    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                showFileChooser();
                break;

            case R.id.submit:
                image_url=getStringImage(bitmap);
                HashMap postdata = new HashMap();
                postdata.put("title",title.getText().toString());
                postdata.put("description",description.getText().toString());
                postdata.put("price",price.getText().toString());
                postdata.put("mobile","android");
                postdata.put("image",image_url);
                PostResponseAsyncTask task = new PostResponseAsyncTask(this, postdata, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                        if(s.equals("success")){
                            Toast.makeText(PracActivity.this,"Product Inserted Successfully",Toast.LENGTH_LONG).show();

                            Intent i = new Intent(PracActivity.this,ListActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(PracActivity.this,s,Toast.LENGTH_LONG).show();
                        }
                    }
                });
                task.execute("http://192.168.64.50/AndroidLogin/insert_product1.php");

                break;
        }
    }



}
