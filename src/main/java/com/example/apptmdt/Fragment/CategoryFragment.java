package com.example.apptmdt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apptmdt.Activity.ListproductActiviti;
import com.example.apptmdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class CategoryFragment extends Fragment {
    private ImageView hp_cate_phone, hp_cate_pc, hp_cate_electronic, hp_cate_sport, hp_cate_book, hp_cate_fashion;
    private FirebaseFirestore db;
    private LinearLayout cate_phone;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category,container,false);
        hp_cate_phone = view.findViewById(R.id.hp_cate_phone);
        hp_cate_pc = view.findViewById(R.id.hp_cate_pc);
        hp_cate_electronic = view.findViewById(R.id.hp_cate_electronic);
        hp_cate_sport = view.findViewById(R.id.hp_cate_sport);
        hp_cate_fashion = view.findViewById(R.id.hp_cate_fashion);
        hp_cate_book = view.findViewById(R.id.hp_cate_book);
        setimagecategory();
        cate_phone = view.findViewById(R.id.cate_phone);
        cate_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Category_productFragment()).addToBackStack(null).commit();*/
                /*Toast.makeText(getActivity(), "phone", Toast.LENGTH_LONG).show();
                Bundle bundle_phone = new Bundle();
                bundle_phone.putString("type","phone");
                Category_productFragment category_productFragment = new Category_productFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                category_productFragment.setArguments(bundle_phone);
                transaction.replace(R.id.fragment_container,category_productFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
                Intent intent = new Intent(getActivity(), ListproductActiviti.class);
                startActivity(intent);
            }
        });


        return view;
    }

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
                        Log.e("TAG", "No such document");
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
                        Log.e("TAG", "DocumentSnapshot data: " + document.getString("image"));
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
}
