package com.example.user.androidloginwithphp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.androidloginwithphp.model.ListModel;
import com.kosalgeek.android.json.JsonConverter;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 10/20/2017.
 */

public class ListActivity extends AppCompatActivity implements AsyncResponse, AdapterView.OnItemClickListener, View.OnClickListener {
    ListView listView;
    public String LOG = "ListActivity";
    private ArrayList<ListModel> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.listview);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);

//        Data();
        PostResponseAsyncTask task = new PostResponseAsyncTask(ListActivity.this, this);
        task.execute("http://192.168.64.50/AndroidLogin/product.php");
        listView.setOnItemClickListener(this);

    }

    @Override
    public void processFinish(String result) {
        Log.d(result, LOG);
        arrayList = new JsonConverter<ListModel>().toArrayList(result, ListModel.class);
        ListViewAdapter listViewAdapter = new ListViewAdapter(ListActivity.this, arrayList);
        listViewAdapter.notifyDataSetChanged();
        listView.setAdapter(listViewAdapter);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        ListModel productList=  arrayList.get(i);
        Intent in = new Intent(ListActivity.this,DetailActivity.class);
        in.putExtra("product",(Serializable) productList);
        startActivity(in);
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(ListActivity.this,ProductAddActivity.class);
        startActivity(i);
    }
}
