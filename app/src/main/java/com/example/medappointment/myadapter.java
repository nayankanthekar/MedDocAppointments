package com.example.medappointment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder> {

    Context context;
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);


    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull final model model) {

        holder.name.setText(model.getName());
        holder.degree.setText(model.getDegree());
        holder.location.setText(model.getCaddress());
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

//        holder.mycard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context,docDetails.class);
//                intent.putExtra("img",model.getPurl());
//                intent.putExtra("name",model.getName());
//                intent.putExtra("degree",model.getDegree());
//                intent.putExtra("address",model.getCaddress());
//                context.startActivity(intent);
//
//            }
//        });


//        holder.mycard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Intent intent = new Intent(context,docDetails.class);
//                //startActivity(new Intent(getApplicationContext(),mainHome.class));
//                //intent.putExtra("img",model.getPurl());
//                //intent.putExtra("name",model.getName());
//                //intent.putExtra("degree",model.getDegree());
//                //context.startActivity(intent);
//
//            }
//        });




    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);


    }
//
    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,degree,location;
        CardView mycard;
        public myviewholder(@NonNull View itemView) {
            super(itemView);



            img = (CircleImageView) itemView.findViewById(R.id.img);
            name = (TextView)itemView.findViewById(R.id.name);
            degree = (TextView)itemView.findViewById(R.id.Degree);
            location = (TextView)itemView.findViewById(R.id.location);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(,docDetails.class);
////                    intent.putExtra("img",model.getPurl());
////                intent.putExtra("name",model.getName());
////                intent.putExtra("degree",model.getDegree());
////                intent.putExtra("address",model.getCaddress());
//                context.startActivity(intent);
//
////                    Intent intent = new Intent(v.getContext(),docDetails.class);
//////
////                    intent.startActivity();
//                    //startActivity(new Intent(getApplicationContext(),mainHome.class));
//                   // finish();
////                    //i.putExtra("select",model.getAdapterPosition());
////                 //v.getContext().startActivity(i);
////
//                }
//            });

            //itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//
//            onListItemClick.onItemClick(getItem(getAdapterPosition()),getAdapterPosition());
//
//        }
    }

//    public interface OnListItemClick{
//        void onItemClick(DocumentSnapshot snapshot,int position);
//        //Intent intent = new Intent(DisplayProducts, ProductDetails.class);
//        // startActivity(intent);
//    }
}
