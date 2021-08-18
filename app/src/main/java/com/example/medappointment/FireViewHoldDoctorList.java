package com.example.medappointment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FireViewHoldDoctorList extends RecyclerView.ViewHolder {
    TextView Name, Degree, location;
    CircleImageView doctorProfile;

    public FireViewHoldDoctorList(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.name);
        Degree = itemView.findViewById(R.id.Degree);
        location = itemView.findViewById(R.id.location);
        doctorProfile = itemView.findViewById(R.id.img);
    }
}
