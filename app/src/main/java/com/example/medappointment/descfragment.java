package com.example.medappointment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class descfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String name, Degree, Caddress, purl;

    public descfragment() {
        // Required empty public constructor
    }

    public descfragment(String name,String Degree,String Caddress,String purl) {
        // Required empty public constructor
        this.name = name;
        this.Degree = Degree;
        this.Caddress = Caddress;
        this.purl = purl;
    }


    // TODO: Rename and change types and number of parameters
    public static descfragment newInstance(String param1, String param2) {
        descfragment fragment = new descfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_descfragment, container, false);

        ImageView picture = view.findViewById(R.id.picture);
        TextView dname = view.findViewById(R.id.dname);
        TextView ddegree = view.findViewById(R.id.ddegree);
        TextView daddress = view.findViewById(R.id.daddress);

        dname.setText(name);
        ddegree.setText(Degree);
        daddress.setText(Caddress);
        Glide.with(getContext()).load(purl).into(picture);

        return view;
    }

    public  void  onBackPressed()
    {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).addToBackStack(null).commit();


    }
}