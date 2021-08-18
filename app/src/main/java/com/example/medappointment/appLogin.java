package com.example.medappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class appLogin extends AppCompatActivity {
    private FirebaseUser user;

    TextView mRegister;

    Button mLogin;
    EditText mEmail, mPassword;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.signup_btn);
        mRegister = findViewById(R.id.clk_register);
        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(appLogin.this, appHome.class));
            finish();
        }
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email Is Required!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password Is Required!");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be more than 6 characters");
                    return;
                }
                //Authenticate the user
                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(appLogin.this, "Login Succesful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), appHome.class));
                            finish();
                        } else {
                            Toast.makeText(appLogin.this, "Error! Unable To Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(appLogin.this, appRegister.class);
//                startActivity(intent);

                startActivity(new Intent(getApplicationContext(), appRegister.class));
            }
        });


    }

}