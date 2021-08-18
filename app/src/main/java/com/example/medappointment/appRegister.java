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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class appRegister extends AppCompatActivity {

    EditText mFullName,mEmail,mPassword,mPhone,mUser,mAddress;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_register);

        mFullName = (EditText) findViewById(R.id.edttxtname);
        mEmail = (EditText)findViewById(R.id.edttxtemail);
        mPassword = (EditText)findViewById(R.id.edttxtpassword);
        mPhone = (EditText)findViewById(R.id.edttxtmobile);
        mUser = (EditText)findViewById(R.id.username);
        mAddress = (EditText)findViewById(R.id.edttxtaddress);
        mRegisterBtn = findViewById(R.id.btnregister1);
        mLoginBtn = findViewById(R.id.alreadyregister);

        rootNode = FirebaseDatabase.getInstance();

        reference = rootNode.getReference().child("Users");

        //reference.setValue("First User");


//        String name = mFullName.getEditableText().toString().trim();
//        String username = mUser.getEditableText().toString().trim();
//        String email = mEmail.getEditableText().toString().trim();
//        String phoneNo = mPhone.getEditableText().toString().trim();
//        String address = mAddress.getEditableText().toString().trim();

//name,username,email,phoneNo,address
 //       userHelperClass helperClass = new userHelperClass();
//        helperClass.getName();
//        helperClass.getUsername();
//        helperClass.getEmail();
//        helperClass.getPhoneNo();
//        helperClass.getAddress();
//        helperClass.setName(mFullName.getText().toString());
//        helperClass.setUsername(mUser.getText().toString());
//        helperClass.setEmail(mEmail.getText().toString());
//        helperClass.setAddress(mAddress.getText().toString());
//        helperClass.setPhoneNo(mPhone.getText().toString());

        //reference.push().setValue(helperClass);


        //reference.child(username).setValue(helperClass);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),appHome.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email Is Required!");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password Is Required!");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be more than 6 characters");
                    return;
                }
                //Register user
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            insertUserData();
                            Toast.makeText(appRegister.this,"Registeration Succesful!",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),appHome.class));
                            finish();
                        }else{

                            Toast.makeText(appRegister.this,"Error! Unable To Register",Toast.LENGTH_SHORT).show();

                        }

                    }
                });





            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),appLogin.class));
            }
        });

    }
    private void insertUserData(){
        String name = mFullName.getEditableText().toString().trim();
        String username = mUser.getEditableText().toString().trim();
        String email = mEmail.getEditableText().toString().trim();
        String phoneNo = mPhone.getEditableText().toString().trim();
        String address = mAddress.getEditableText().toString().trim();

        userHelperClass helperClass = new userHelperClass(name,username,email,phoneNo,address);
        reference.child(fAuth.getCurrentUser().getUid()).setValue(helperClass);

    }
}