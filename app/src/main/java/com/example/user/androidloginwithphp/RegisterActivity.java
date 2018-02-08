package com.example.user.androidloginwithphp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Created by User on 10/19/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    EditText email,password;
    Button register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText)findViewById(R.id.email_reg);
        password = (EditText) findViewById(R.id.passsword_reg);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        HashMap postdata= new HashMap();
        postdata.put("mobile","android");
        postdata.put("email",email.getText().toString());
        postdata.put("password",password.getText().toString());

        PostResponseAsyncTask task = new PostResponseAsyncTask(this,postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                if(s.contains("success")){

                    Toast.makeText(RegisterActivity.this,"Register Successfully",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this,s,Toast.LENGTH_LONG).show();
                }
            }
        });
        task.execute("http://192.168.64.50/AndroidLogin/register.php");
    }
}
