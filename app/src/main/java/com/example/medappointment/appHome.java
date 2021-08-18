package com.example.medappointment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class appHome extends AppCompatActivity {
    //Varibles for Profile of user
    RecyclerView recyclerView;
    ArrayList<uploadDoctor> arrayListHistory;
    FirebaseRecyclerOptions<uploadDoctor> options;
    FirebaseRecyclerAdapter<uploadDoctor, FireViewHoldAppointmentList> adapterHistory;
    private FirebaseUser user;
    private DatabaseReference reference, reference2;
    private String userID;
    //For uploading image
    Button upload, logout, search;
    ImageView profilePic;
    StorageReference storage;
    public Uri imguri;
    TextView textCount;
    private StorageTask uploadTask;
    TextView unameTextView, uemailTextView, uphoneTextView, uaddressTextView;
    int totalAppointmentCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Home Screen");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(appHome.this));
        recyclerView.setHasFixedSize(true);
        textCount = findViewById(R.id.textCount);
        arrayListHistory = new ArrayList<uploadDoctor>();
//        -------------Code for Uploding Image--------------------------
        storage = FirebaseStorage.getInstance().getReference("Images");
        //upload = (Button)findViewById(R.id.uploadbutton);
        profilePic = (ImageView) findViewById(R.id.imageView2);
        // search = (Button)findViewById(R.id.search);

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Filechooser();
//            }
//        });
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (uploadTask != null && uploadTask.isInProgress())
//                {
//                    Toast.makeText(appHome.this,"Upload in progress",Toast.LENGTH_SHORT).show();
//                }else
//                {
//                    Fileuploader();
//                }
//
//            }
//        });

        //-----------------------**--------------------------------
        //Code to display loged in users information on profile page
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        unameTextView = (TextView) findViewById(R.id.uname);
        uemailTextView = (TextView) findViewById(R.id.uemail);
        uphoneTextView = (TextView) findViewById(R.id.uphone);
        uaddressTextView = (TextView) findViewById(R.id.uaddress);

        reference2 = FirebaseDatabase.getInstance().getReference("Users").child(userID).child("appointments");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    totalAppointmentCount = (int) dataSnapshot.getChildrenCount();
                    textCount.setText("Appointment Count: "+totalAppointmentCount);
                } else {
                    textCount.setText("Appointment Count: "+totalAppointmentCount);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String unamep, uemailp, uphonep, uaddressp;

                    unamep = snapshot.child("name").getValue().toString();
                    uemailp = snapshot.child("email").getValue().toString();
                    uphonep = snapshot.child("phoneNo").getValue().toString();
                    uaddressp = snapshot.child("address").getValue().toString();

                    unameTextView.setText(unamep);
                    uemailTextView.setText(uemailp);
                    uphoneTextView.setText(uphonep);
                    uaddressTextView.setText(uaddressp);
                }
                else {
                    Toast.makeText(appHome.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(appHome.this, "Error in loding profile", Toast.LENGTH_LONG).show();
            }
        });

        LoadData("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            profilePic.setImageURI(imguri);
        }
    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void Filechooser() {
        Intent intent = new Intent();
        intent.setType("/image*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }


    public void getStarted(View view) {
        startActivity(new Intent(getApplicationContext(), DoctorList.class));
    }

    public void Fileuploader() {
        StorageReference Ref = storage.child(System.currentTimeMillis() + "." + getExtension(imguri));

        uploadTask = Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(appHome.this);
        builder.setMessage("Are you sure want to exit from app?");
        builder.setCancelable(false);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //here exit app alert close............................................
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), appLogin.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void LoadData(final String data) {
        Query query1 = reference.child(userID).child("appointments");
        options = new FirebaseRecyclerOptions.Builder<uploadDoctor>().setQuery(query1, uploadDoctor.class).build();
        adapterHistory = new FirebaseRecyclerAdapter<uploadDoctor, FireViewHoldAppointmentList>(options) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void onBindViewHolder(@NonNull FireViewHoldAppointmentList holder, int position, @NonNull uploadDoctor model) {
                holder.Data.setText("You have an appointment with\n-------------------------------------------------- \nDr. "
                        +model.getName()+"\nClinic-"+model.getCName()
                        +"\nOn "+model.getPurl()+", in between "+model.getTimeSlot());
            }

            @NonNull
            @Override
            public FireViewHoldAppointmentList onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                return new FireViewHoldAppointmentList(LayoutInflater.from(appHome.this).inflate(R.layout.secondrow, viewGroup, false));
            }
        };
        adapterHistory.startListening();
        recyclerView.setAdapter(adapterHistory);

    }


}