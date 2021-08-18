package com.example.medappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class docDetails extends AppCompatActivity {

    ImageView img;
    TextView name,degree,address;
    String docName,docDegree,docAddress,docImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_details);

        img = findViewById(R.id.picture);
        name = findViewById(R.id.dname);
        degree = findViewById(R.id.ddegree);
        address = findViewById(R.id.daddress);

        docImg = getIntent().getStringExtra("img");
        docName = getIntent().getStringExtra("name");
        docDegree = getIntent().getStringExtra("degree");
        docAddress = getIntent().getStringExtra("address");


        name.setText(docName);
        degree.setText(docDegree);
        address.setText(docAddress);
        img.setImageResource(Integer.parseInt(docImg));


    }
}