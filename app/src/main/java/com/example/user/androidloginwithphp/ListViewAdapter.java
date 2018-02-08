package com.example.user.androidloginwithphp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.androidloginwithphp.model.ListModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by User on 10/20/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    Activity activity;
    ArrayList<ListModel> arrayList;
    LayoutInflater inflater;
    TextView title,desc,price;
    ImageView imageView;
    public ListViewAdapter(Activity activity,ArrayList<ListModel> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ListModel getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview_item,null);
        title  = (TextView)view.findViewById(R.id.title);
        desc = (TextView)view.findViewById(R.id.description);
        imageView=(ImageView)view.findViewById(R.id.image);
        price = (TextView)view.findViewById(R.id.price);
        ListModel listModel = getItem(i);

        title.setText(listModel.title);
        desc.setText(listModel.description);
        price.setText(String.valueOf(listModel.price));
        Picasso.with(activity).load(listModel.image_url).into(imageView);

//        String image_url = listModel.image_url;
//        byte[] decodedByte = Base64.decode(image_url, Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
//        imageView.setImageBitmap(bitmap);


        return view;
    }
}
