package com.example.apptmdt;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apptmdt.Interface.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter  {
    private Context context;
    private int layout;
    private ArrayList<Product> listData;

    View view;
    LayoutInflater layoutInflater;
    public ProductAdapter(Context context, int layout, ArrayList<Product> listData) {
        this.context = context;
        this.layout = layout;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
           view = new  View(context);
           view = layoutInflater.inflate(R.layout.hp_pd_gridview,null);;
            ImageView imageView = view.findViewById(R.id.grid_img);

            TextView productname = view.findViewById(R.id.product_name);
            TextView productprice= view.findViewById(R.id.product_price);
            productname.setText(listData.get(position).getName());
            productprice.setText(listData.get(position).getPrice());
            Picasso.get().load(String.valueOf(listData.get(position).getImg_url().get(position))).into(imageView);

        }

        return view;
    }


}
