package com.example.medappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class DoctorList extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<uploadDoctor> arrayListHistory;
    FirebaseRecyclerOptions<uploadDoctor> options;
    FirebaseRecyclerAdapter<uploadDoctor, FireViewHoldDoctorList> adapterHistory;
    EditText searchDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        reference = FirebaseDatabase.getInstance().getReference("Doctors");
        searchDoctor = findViewById(R.id.searchViewDoctor);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(DoctorList.this));
        recyclerView.setHasFixedSize(true);

        arrayListHistory = new ArrayList<uploadDoctor>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Doctor List");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LoadData("");
        searchDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString() != null) {
                    LoadData(s.toString());
                } else {
                    LoadData("");
                }
            }
        });
    }

    private void LoadData(final String data) {
        Query query1 = reference.orderByChild("Name").startAt(data).endAt(data + "\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<uploadDoctor>().setQuery(query1, uploadDoctor.class).build();
        adapterHistory = new FirebaseRecyclerAdapter<uploadDoctor, FireViewHoldDoctorList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FireViewHoldDoctorList holder, int position, @NonNull final uploadDoctor model) {
                holder.Name.setText(model.getName());
                holder.Degree.setText(model.getDegree());
                holder.location.setText(model.getCName()+" "+model.getCaddress());
                Picasso.get().load(model.purl).into(holder.doctorProfile);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(model.getName().equals("Jayashri Sonawne")){
                            Intent intent = new Intent(DoctorList.this, BookAppointment.class);
                            intent.putExtra("docNo", "Doc1");
                            startActivity(intent);
                        }
                        else if(model.getName().equals("Vaishali Chavan")){
                            Intent intent = new Intent(DoctorList.this, BookAppointment.class);
                            intent.putExtra("docNo", "Doc2");
                            startActivity(intent);
                        }
                        else if(model.getName().equals("Archana Shinde")){
                            Intent intent = new Intent(DoctorList.this, BookAppointment.class);
                            intent.putExtra("docNo", "Doc3");
                            startActivity(intent);
                        }
                    }
                });
            }


            @NonNull
            @Override
            public FireViewHoldDoctorList onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
                return new FireViewHoldDoctorList(LayoutInflater.from(DoctorList.this).inflate(R.layout.singlerow, viewGroup, false));
            }
        };
        adapterHistory.startListening();
        recyclerView.setAdapter(adapterHistory);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}