package com.example.apptmdt.Activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apptmdt.Fragment.LoginFragment;
import com.example.apptmdt.R;
import com.example.apptmdt.Fragment.SignupFragment;
import com.squareup.picasso.Picasso;

public class Login_SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean flag = true;
    private Button btn_login, btn_signup;
    private ImageView topimage;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__signin);

        btn_login = findViewById(R.id.btn_login);
        btn_signup = findViewById(R.id.btn_signup);
        topimage = findViewById(R.id.imagetop);
        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/apptmdt-11219.appspot.com/o/tiki.png?alt=media&token=d8e80f87-4608-4f7f-9b68-8608c637c86c")
                .into(topimage);
       btn_login.setOnClickListener(this);
       btn_login.setTextColor(Color.parseColor("#1FCCE7"));
       btn_signup.setOnClickListener(this);
       if(savedInstanceState == null){
           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_loginsignup,
                   new LoginFragment()).commit();
            flag = false;
       }


    } //cloase onCreate


    @Override
    public void onClick(View v) {
        if(flag){
           // btn_signup.setTextColor(Color.parseColor("#000000"));
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_loginsignup,
                    new LoginFragment()).commit();
            flag = false;
            btn_login.setTextColor(Color.parseColor("#1FCCE7"));
            btn_signup.setTextColor(Color.parseColor("#000000"));
        }
        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_loginsignup,
                    new SignupFragment()).commit();
            flag = true;
            btn_signup.setTextColor(Color.parseColor("#1FCCE7"));
            btn_login.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}// close class
