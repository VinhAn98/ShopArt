package com.example.apptmdt.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.apptmdt.Fragment.Category_productFragment;
import com.example.apptmdt.Fragment.HomepageFragment;
import com.example.apptmdt.Fragment.ProductdetailFragment;
import com.example.apptmdt.Interface.ItemClickListener;
import com.example.apptmdt.Model.CurrentItem;
import com.example.apptmdt.Product;
import com.example.apptmdt.ProductListAdapter;
import com.example.apptmdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.example.apptmdt.ProductListAdapter.SPAN_COUNT_ONE;
import static com.example.apptmdt.ProductListAdapter.SPAN_COUNT_TWO;

public class ListproductActiviti extends AppCompatActivity implements OnCompleteListener<QuerySnapshot>, ItemClickListener {

    private Toolbar toolbar;
    private int temp = 1;
    private RecyclerView recyclerView;
    private ArrayList<Product> productslist;
    private GridLayoutManager gridLayoutManager;
    private FirebaseFirestore db;
    private ProductListAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listproduct_activiti);
        recyclerView = findViewById(R.id.product_list);
        toolbar = findViewById(R.id.toolbarlist);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.changelist_menu);
        }

        getinfoproduct();
        toolbarclick();
    }

    private void toolbarclick() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (temp == 1 ) {
                    menuItem.setIcon(R.drawable.ic_grid);
                    temp = 2;
                    switchLayout();
                }
                else {
                    menuItem.setIcon(R.drawable.ic_list);
                    temp = 1;
                    switchLayout();
                }
                return false;
            }
        });

    }

    private void getinfoproduct() {
        productslist = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("Product")
                .get()
                .addOnCompleteListener(this);


    }





    private void switchLayout(){
        if(gridLayoutManager.getSpanCount() == SPAN_COUNT_ONE){
            gridLayoutManager.setSpanCount(SPAN_COUNT_TWO);
        }else {
            gridLayoutManager.setSpanCount(SPAN_COUNT_ONE);
        }
        adapter.notifyItemRangeChanged(0,adapter.getItemCount());
}
    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot document : task.getResult()) {
                productslist.add(new Product(
                        document.getString("id-product"),
                        document.getString("id-sub"),
                        document.getString("Name"),
                        document.getString("price"),
                        (List<String>) document.get("image"),
                        (Map) document.get("info")
                ));

            }
            gridLayoutManager = new GridLayoutManager(this,SPAN_COUNT_ONE , GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.HORIZONTAL));
            adapter = new ProductListAdapter(this, productslist,gridLayoutManager);
            adapter.changegrid();
            adapter.setClickListener(this);
            recyclerView.setAdapter(adapter);
            Log.e("TAG", "pproduct =>" + productslist.get(0));
        } else {
            Log.e("TAG", "Error getting documents: ", task.getException());
        }
    }

    @Override
    public void onItemClick(View view, int position) {


        CurrentItem.currenitem = productslist.get(position);
        Intent intent = new Intent(this, ProductDetail.class);
        startActivity(intent);
    }
}

