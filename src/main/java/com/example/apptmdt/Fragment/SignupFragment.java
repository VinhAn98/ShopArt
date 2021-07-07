package com.example.apptmdt.Fragment;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apptmdt.Activity.Login_SignupActivity;
import com.example.apptmdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupFragment extends Fragment {

    private EditText signup_name, signup_number, signup_email, signup_pass;
    private Button signup_btn;
    private FirebaseAuth mAuth;
    private String email, pass, number, name;
    private FirebaseUser user;
    private FirebaseFirestore db;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        mAuth = FirebaseAuth.getInstance();
        signup_name = view.findViewById(R.id.signup_name);
        signup_number = view.findViewById(R.id.signup_number);
        signup_email = view.findViewById(R.id.signup_email);
        signup_pass = view.findViewById(R.id.signup_pass);
        signup_btn = view.findViewById(R.id.signup_btn);
        db = FirebaseFirestore.getInstance();
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = signup_email.getText().toString();
                pass = signup_pass.getText().toString();
                number = signup_number.getText().toString();
                name = signup_name.getText().toString();
                setName(name);
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(getContext(),"signup success",Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg"))
                                    .build();
                            user.updateProfile(profileUpdates)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.e("TAG", "User profile updated.");
                                            }
                                        }
                                    });

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "signup fail", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


                LoginFragment category_productFragment = new LoginFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_loginsignup, category_productFragment);
                transaction.commit();
            }
        });


        return view;
    }

    private void updateinfo() {


    }
}
