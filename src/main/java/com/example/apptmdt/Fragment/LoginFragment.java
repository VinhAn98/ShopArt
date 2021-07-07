package com.example.apptmdt.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptmdt.Activity.ListproductActiviti;
import com.example.apptmdt.Activity.MainActivity;
import com.example.apptmdt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    private EditText login_email,login_pass;
    private Button login_btn;
    private FirebaseAuth firebaseAuth;
    private TextView login_error;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        login_email = view.findViewById(R.id.login_email);
        login_pass = view.findViewById(R.id.login_pass);
        login_btn = view.findViewById(R.id.login_btn);
        login_error = view.findViewById(R.id.login_error);
        firebaseAuth = FirebaseAuth.getInstance();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(login_email.getText().toString(),login_pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    intent.putExtra("login","success");
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(getContext(),"TEST_LOGIN",Toast.LENGTH_SHORT).show();
                                    login_error.setVisibility(View.VISIBLE);
                                }

                            }
                        });
            }
        });

        return view;
    }
}
