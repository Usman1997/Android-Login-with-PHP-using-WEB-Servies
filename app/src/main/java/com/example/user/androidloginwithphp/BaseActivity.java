package com.example.user.androidloginwithphp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;

import java.io.IOException;

/**
 * Created by User on 10/21/2017.
 */

public class BaseActivity extends AppCompatActivity {
    final int CAMERA_REQUEST = 12321;
    final int GALLERY_REQUST = 21234;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;



    public boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches();
    }


    public void Dialog_image(final Context context){


        ImageView gallery,camera;
        final Dialog dialog = new Dialog(context,android.R.style.Theme_Light_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_box_image);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        gallery = (ImageView)dialog.findViewById(R.id.gallery);
        camera = (ImageView)dialog.findViewById(R.id.camera);
        cameraPhoto = new CameraPhoto(context);
        galleryPhoto = new GalleryPhoto(context);
        dialog.show();
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean diditwork = true;
                try {

                    startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(context,"Error in capturing picture",Toast.LENGTH_SHORT).show();
                    diditwork = false;
                }finally {
                    if(diditwork){
                        dialog.dismiss();
                    }
                }
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean diditwork = true;
                try {
                    startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUST);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(context,"Error in capturing picture",Toast.LENGTH_SHORT).show();
                    diditwork=false;
                }finally {
                    if(diditwork){
                        dialog.dismiss();
                    }

                }
            }
        });
        return;

    }


}
