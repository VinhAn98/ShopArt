package com.example.apptmdt.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.apptmdt.Fragment.CategoryFragment;
import com.example.apptmdt.Fragment.HomepageFragment;
import com.example.apptmdt.Fragment.ProfileFragment;
import com.example.apptmdt.Fragment.SignupFragment;
import com.example.apptmdt.Model.CurrentUser;
import com.example.apptmdt.Model.User;
import com.example.apptmdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private boolean isLogin = false;
    private TextView navusername;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private FirebaseFirestore db;
    private SignupFragment fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_item);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomepageFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        firebaseAuth = FirebaseAuth.getInstance();


       // Log.e("TAGG",name);
        login();

    }//cloase on create

    private void login() {
        View headerview = navigationView.getHeaderView(0);
        String temp;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            temp = null;
        } else {
            temp = extras.getString("login");

            if (temp.equals("success")) {
                isLogin = true;

            }

        }

        if (isLogin) {
            // da login onlick vao ra frameng profile info
           //updateinfo();
            firebaseUser = firebaseAuth.getCurrentUser();
            navusername = (TextView) headerview.findViewById(R.id.account_name);
            navusername.setText(firebaseUser.getDisplayName());
            firebaseUser = firebaseAuth.getCurrentUser();

            TextView navuseremail = (TextView) headerview.findViewById(R.id.account_email);
            navuseremail.setVisibility(View.VISIBLE);
            navuseremail.setText(firebaseUser.getEmail());

            //Log.e("TAG","test" + user);

        } else {
            //guest login
            headerview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Login_SignupActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


    private void updateinfo() {
/*      UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Log.e("TAG", "User profile updated.");
                        }
                    }
                });
        Log.e("tag", "test" + user.getDisplayName());*/


    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomepageFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_category:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CategoryFragment()).addToBackStack(null).commit();
                break;
            case R.id.nav_account:
                if (!isLogin) {
                    Intent intent = new Intent(MainActivity.this, Login_SignupActivity.class);
                    startActivity(intent);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ProfileFragment()).addToBackStack(null).commit();
                }

                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
