package com.example.user.androidloginwithphp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText email,password;
    Button login,register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.passsword);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {


        switch (view.getId()){
            case R.id.login:
                HashMap postdata = new HashMap();
                postdata.put("mobile", "android");
                postdata.put("email", email.getText().toString());
                postdata.put("password", password.getText().toString());
                PostResponseAsyncTask task = new PostResponseAsyncTask(this,postdata, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        if(s.contains("success")){

                           Intent i = new Intent(MainActivity.this,ListActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                        }

                    }
                });
                task.execute("http://192.168.64.50/AndroidLogin/login.php");
             break;


            case R.id.register:
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
                break;


        }
    }




}
