package com.example.apptmdt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.apptmdt.Interface.ItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Product> data;
    private ItemClickListener mClickListener;
    public static final int SPAN_COUNT_ONE =1;
    public static final int SPAN_COUNT_TWO =2;
    private final int LAYOUT_LIST = 1;
    private final int LAYOUT_GIRD = 2;
    private int gridlayout = 2;
    private GridLayoutManager gridLayoutManager;

    @Override
    public int getItemViewType(int position) {
        int spanCount = gridLayoutManager.getSpanCount();
        if(spanCount == SPAN_COUNT_ONE){
            return LAYOUT_GIRD;
        }else {
            return LAYOUT_LIST;
        }
    }

    public ProductListAdapter(Context context, ArrayList<Product> data,GridLayoutManager gridLayoutManager) {
        this.context = context;
        this.data = data;
        this.gridLayoutManager = gridLayoutManager;
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (gridlayout == LAYOUT_GIRD) {
            View view = LayoutInflater.from(context).inflate(R.layout.hp_pd_gridview, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.list_all_product, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.product_name.setText(data.get(position).getName());
        holder.produc_price.setText(data.get(position).getPrice());
        Picasso.get().load(data.get(position).getImg_url().get(0)).into(holder.imageView);
        holder.setClickListener(mClickListener);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public void changegrid(){
        if(gridlayout == 2){
            gridlayout = 1;
        }else{
            gridlayout = 2;
        }


    }





    // start class viewholder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;
        TextView product_name, produc_price;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (gridlayout == LAYOUT_GIRD) {
                product_name = itemView.findViewById(R.id.product_name);
                produc_price = itemView.findViewById(R.id.product_price);
                imageView = itemView.findViewById(R.id.grid_img);
                itemView.setOnClickListener(this);
            } else {
                product_name = itemView.findViewById(R.id.product_name_list);
                produc_price = itemView.findViewById(R.id.product_price_list);
                imageView = itemView.findViewById(R.id.product_image_list);
                itemView.setOnClickListener(this);
            }


        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    } // cloase on viewholder


}
