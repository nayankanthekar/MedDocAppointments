package com.example.medappointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class mainHome extends AppCompatActivity

{
    RecyclerView recview;



    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        recview = (RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Doctors"), model.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    @Override
//    public void onItemClick(DocumentSnapshot snapshot,int position) {
//        Log.d("Im clicked","Clicked!!"+ position+ "ID is "+ snapshot.getId());
//
//
//
//        Intent intent = new Intent(this, docDetails.class);
//        //intent.putExtra("Selected Product",);
//        //intent.putExtra("Selected Product",snapshot.getId());
//        startActivity(intent);
//
//    }
}