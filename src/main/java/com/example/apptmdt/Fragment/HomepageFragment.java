package com.example.apptmdt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.apptmdt.Activity.ListproductActiviti;
import com.example.apptmdt.Activity.ProductDetail;
import com.example.apptmdt.Fragment.ExpandableHeightGridView;
import com.example.apptmdt.Interface.ItemClickListener;
import com.example.apptmdt.Model.CurrentItem;
import com.example.apptmdt.Product;
import com.example.apptmdt.ProductAdapter;
import com.example.apptmdt.ProductListAdapter;
import com.example.apptmdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.example.apptmdt.ProductListAdapter.SPAN_COUNT_ONE;
import static com.example.apptmdt.ProductListAdapter.SPAN_COUNT_TWO;
import static android.support.constraint.Constraints.TAG;

public class HomepageFragment extends Fragment implements ItemClickListener, OnCompleteListener<QuerySnapshot> {
    private CarouselView carouselView;
    private LinearLayout cate_phone, cate_laptop;
    private ImageView hp_cate_phone, hp_cate_pc, hp_cate_electronic, hp_cate_sport, hp_cate_book, hp_cate_fashion;
    private RecyclerView hp_list;
    private ProductListAdapter adapter;
    private ArrayList<String> sample;
    private FirebaseFirestore db;
    private ArrayList<Product> productslist;
    private GridLayoutManager gridLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        addtopbanel();

        CarouselView carouselView = view.findViewById(R.id.carouselview);
        if (carouselView != null) {
            carouselView.setPageCount(sample.size());
            carouselView.setImageListener(imageListener);
        }
        cate_phone = view.findViewById(R.id.cate_phone);
        cate_laptop = view.findViewById(R.id.cate_laptop);
        hp_cate_phone = view.findViewById(R.id.hp_cate_phone);
        hp_cate_pc = view.findViewById(R.id.hp_cate_pc);
        hp_cate_electronic = view.findViewById(R.id.hp_cate_electronic);
        hp_cate_sport = view.findViewById(R.id.hp_cate_sport);
        hp_cate_fashion = view.findViewById(R.id.hp_cate_fashion);
        hp_cate_book = view.findViewById(R.id.hp_cate_book);
        setimagecategory();
        // click on category
        cate_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ListproductActiviti.class);
                startActivity(intent);
            }
        });
        cate_laptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getActivity(), "laptop", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("type","laptop");
                Category_productFragment category_productFragment = new Category_productFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                category_productFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_container,category_productFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
                //Toast.makeText(getActivity(), "phone", Toast.LENGTH_LONG).show();
                Bundle bundle_phone = new Bundle();
                bundle_phone.putString("type", "phone");
                Category_productFragment category_productFragment = new Category_productFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                category_productFragment.setArguments(bundle_phone);
                transaction.replace(R.id.fragment_container, category_productFragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });
        hp_list = view.findViewById(R.id.hp_list);
        getinfoproduct();
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {

                Intent intent = new Intent(getActivity(), ListproductActiviti.class);
                startActivity(intent);
            }
        });

        return view;
    } // cloase onCreateView

    private void getinfoproduct() {
        productslist = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        db.collection("Product")
                .get()
                .addOnCompleteListener(this);


    }

    private void addtopbanel() {
        sample = new ArrayList<>();
        sample.add("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/top-banel%2Fbookdealfix.jpg?alt=media&token=fe90e0d1-bf9f-452a-870b-a3c730a9f4d2");
        sample.add("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/top-banel%2Fbooksalefix.png?alt=media&token=87f01adc-12e8-4379-958e-c60ba69923f7");
        sample.add("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/top-banel%2Felectronicdealfix.jpg?alt=media&token=3887b798-bd19-488f-b6c7-f105a5cbf646");
        sample.add("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/top-banel%2Flaptopdeal.jpg?alt=media&token=8ed7b091-4490-4cc4-89d3-d2e4f40cf846");
        sample.add("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/top-banel%2Fphonedealfix.jpg?alt=media&token=d1ce23b9-9989-4801-9e9a-393e9f44e671");
        sample.add("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/top-banel%2Fsamsungfix.jpg?alt=media&token=e8008cf7-40cc-4cdb-88be-85dfed0a5f0d");
    }

    // using picasso
    private void setimagecategory() {
        // phone
        db = FirebaseFirestore.getInstance();
        DocumentReference image_1 = db.collection("Category").document("Category-1");
        image_1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Picasso.get().load(document.getString("image")).into(hp_cate_phone);

                    } else {

                    }
                } else {
                    Log.e("TAG", "get failed with ", task.getException());
                }
            }
        });// image 1

        //Sách
        DocumentReference image_2 = db.collection("Category").document("category-2");
        image_2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        Picasso.get().load(document.getString("image")).into(hp_cate_book);
                    } else {
                        Log.e("TAG", "No such document");
                    }
                } else {
                    Log.e("TAG", "get failed with ", task.getException());
                }
            }
        });// image 2

        //laptop
        DocumentReference image_3 = db.collection("Category").document("category-3");
        image_3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Picasso.get().load(document.getString("image")).into(hp_cate_pc);
                    } else {
                        Log.e("TAG", "No such document");
                    }
                } else {
                    Log.e("TAG", "get failed with ", task.getException());
                }
            }
        });// image 3

        //fashion
        DocumentReference image_4 = db.collection("Category").document("category-4");
        image_4.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Picasso.get().load(document.getString("image")).into(hp_cate_fashion);
                    } else {
                        Log.e("TAG", "No such document");
                    }
                } else {
                    Log.e("TAG", "get failed with ", task.getException());
                }
            }
        });// image 4

        //electronic
        DocumentReference image_5 = db.collection("Category").document("category-5");
        image_5.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Picasso.get().load(document.getString("image")).into(hp_cate_electronic);
                    } else {
                        Log.e("TAG", "No such document");
                    }
                } else {
                    Log.e("TAG", "get failed with ", task.getException());
                }
            }
        });// image 5

        //sport
        DocumentReference image_6 = db.collection("Category").document("category-6");
        image_6.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Picasso.get().load(document.getString("image")).into(hp_cate_sport);
                    } else {
                        Log.e("TAG", "No such document");
                    }
                } else {
                    Log.e("TAG", "get failed with ", task.getException());
                }
            }
        });// image 6


    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageResource(sampleimg[position]);
            Picasso.get().load(sample.get(position)).into(imageView);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    } // close onViewCreated


    @Override
    public void onItemClick(View view, int position) {
        CurrentItem.currenitem = productslist.get(position);

        Intent intent = new Intent(getActivity(), ProductDetail.class);
        startActivity(intent);
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
            gridLayoutManager = new GridLayoutManager(getContext(),SPAN_COUNT_TWO, GridLayoutManager.VERTICAL, false);
            hp_list.setLayoutManager(gridLayoutManager);
            hp_list.setHasFixedSize(true);
            hp_list.addItemDecoration(new DividerItemDecoration(hp_list.getContext(),DividerItemDecoration.VERTICAL));
            hp_list.addItemDecoration(new DividerItemDecoration(hp_list.getContext(),DividerItemDecoration.HORIZONTAL));
            adapter = new ProductListAdapter(getActivity(), productslist,gridLayoutManager);
            adapter.setClickListener(this);
            hp_list.setAdapter(adapter);

        } else {
            Log.e("TAG", "Error getting documents: ", task.getException());
        }
    }



}
