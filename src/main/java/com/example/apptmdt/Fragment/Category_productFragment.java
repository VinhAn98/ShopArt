package com.example.apptmdt.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.apptmdt.Interface.ItemClickListener;
import com.example.apptmdt.Product;
import com.example.apptmdt.ProductListAdapter;
import com.example.apptmdt.R;

import java.util.ArrayList;

public class Category_productFragment extends Fragment implements ItemClickListener {
    private RecyclerView recyclerView;
    private ProductListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_product,container,false);
        recyclerView = view.findViewById(R.id.listproduct);
        /*String str = new String();
        str =getArguments().getString("type");
        Log.e("tag",str);
        switch (str){
            case "laptop":
                ArrayList<Product> img = new ArrayList<>();
                img.add(new Product("1", "s1", "1", "phone", "ss8", "800k"));
                img.add(new Product("1", "s1", "1", "phone", "ss8", "800k"));
                img.add(new Product("1", "s1", "1", "phone", "ss5", "850k"));
                img.add(new Product("1", "s1", "1", "phone", "ss2", "860k"));
                int colum = 1;
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),colum));
                recyclerView.setHasFixedSize(true);
                adapter = new ProductListAdapter(getActivity(),img);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);
                break;
            case "phone":
                ArrayList<Product> img_phone = new ArrayList<>();
                img_phone.add(new Product("1", "s1", "1", "phone", "ss8", "800k"));
                img_phone.add(new Product("1", "s1", "1", "phone", "ss3", "800k"));
                img_phone.add(new Product("1", "s1", "1", "phone", "ss5", "850k"));
                img_phone.add(new Product("1", "s1", "1", "phone", "ss9", "860k"));
                int colum_phone =1;
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),colum_phone));
                recyclerView.setHasFixedSize(true);
                adapter = new ProductListAdapter(getActivity(),img_phone);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);
                break;
        }

*/
        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.e("TAG","test");

    }
}
