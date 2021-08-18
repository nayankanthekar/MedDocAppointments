package com.example.medappointment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FireViewHoldAppointmentList extends RecyclerView.ViewHolder {
    TextView Data;

    public FireViewHoldAppointmentList(@NonNull View itemView) {
        super(itemView);
        Data = itemView.findViewById(R.id.data);
    }
}
