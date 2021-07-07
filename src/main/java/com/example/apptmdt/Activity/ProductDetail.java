package com.example.apptmdt.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptmdt.Model.CurrentItem;
import com.example.apptmdt.R;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductDetail extends AppCompatActivity {
    private CarouselView product_detail_cv;
    private TextView product_detail_name,product_detail_price;
    private ArrayList<String> listimage;
    private TextView info_1,info_2,info_3,info_4,info_5,info_6,info_7,info_8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        product_detail_cv = findViewById(R.id.product_detail_cv);
        Getimage();
        if (product_detail_cv != null) {
            product_detail_cv.setPageCount(listimage.size());
            product_detail_cv.setImageListener(imageListener);
        }
        findidinfo();
        product_detail_name = findViewById(R.id.product_detail_name);
        product_detail_price = findViewById(R.id.product_detail_price);
        product_detail_name.setText(CurrentItem.currenitem.getName());
        product_detail_price.setText(CurrentItem.currenitem.getPrice());


    }


    public void  findidinfo(){
        info_1 = findViewById(R.id.product_info_1);
        info_2 = findViewById(R.id.product_info_2);
        info_3 = findViewById(R.id.product_info_3);
        info_4 = findViewById(R.id.product_info_4);
        info_5 = findViewById(R.id.product_info_5);
        info_6 = findViewById(R.id.product_info_6);
        info_7 = findViewById(R.id.product_info_7);
        info_8 = findViewById(R.id.product_info_8);
        info_1.setText(CurrentItem.currenitem.getInfo().keySet().toArray()[0].toString());
        info_2.setText(CurrentItem.currenitem.getInfo().keySet().toArray()[1].toString());
        info_3.setText(CurrentItem.currenitem.getInfo().keySet().toArray()[2].toString());
        info_4.setText(CurrentItem.currenitem.getInfo().keySet().toArray()[3].toString());
        info_5.setText(CurrentItem.currenitem.getInfo().get(CurrentItem.currenitem.getInfo().keySet().toArray()[0].toString()));
        info_6.setText(CurrentItem.currenitem.getInfo().get(CurrentItem.currenitem.getInfo().keySet().toArray()[1].toString()));
        info_7.setText(CurrentItem.currenitem.getInfo().get(CurrentItem.currenitem.getInfo().keySet().toArray()[2].toString()));
        info_8.setText(CurrentItem.currenitem.getInfo().get(CurrentItem.currenitem.getInfo().keySet().toArray()[3].toString()));
    }
    public void Getimage(){
        listimage = new ArrayList<>();
        for(int i = 0 ; i < CurrentItem.currenitem.getImg_url().size();i++){
            listimage.add(CurrentItem.currenitem.getImg_url().get(i));

        }
    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageResource(sampleimg[position]);
            Picasso.get().load(listimage.get(position)).into(imageView);
        }
    };
}
