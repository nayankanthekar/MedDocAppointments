package com.example.medappointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class BookAppointment extends AppCompatActivity {
    DatabaseReference reference, reference2;
    private FirebaseUser user;
    public TextView selectDate;
    String docNo, name, degree, address,clinicName, profileUrl, selectedSlot = "", userID;
    TextView dName, dDegree, dAddress, slotText, dclinic;
    CircleImageView picture;
    Button slot1, slot2, slot3, slot4;
    String slotS1 = "false", slotS2 = "false", slotS3 = "false", slotS4 = "false";
    LinearLayout layoutSlot;
    int totalAppointmentCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        selectDate = findViewById(R.id.dateAppointment);
        dName = findViewById(R.id.dname);
        dDegree = findViewById(R.id.ddegree);
        dAddress = findViewById(R.id.daddress);
        dclinic = findViewById(R.id.dclinicname);
        picture = findViewById(R.id.picture);
        docNo = getIntent().getStringExtra("docNo");
        reference = FirebaseDatabase.getInstance().getReference("Doctors");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        slot4 = findViewById(R.id.slot4);
        slotText = findViewById(R.id.slotText);
        layoutSlot = findViewById(R.id.layoutSlot);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Book Appointment");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        reference2 = FirebaseDatabase.getInstance().getReference("Users").child(userID).child("appointments");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    totalAppointmentCount = (int) dataSnapshot.getChildrenCount();
                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slotS1.equals("true")) {
                    Toast.makeText(BookAppointment.this, "This slot is booked", Toast.LENGTH_SHORT).show();
                } else {

                    slot1.setBackground(getResources().getDrawable(R.drawable.button2));
                    if (slotS2.equals("true")) {
                        slot2.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot2.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS3.equals("true")) {
                        slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot3.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS4.equals("true")) {
                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot4.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    selectedSlot = "10-11";
                    slotText.setText("You have selected " + selectedSlot);
                }
            }
        });
        slot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slotS2.equals("true")) {
                    Toast.makeText(BookAppointment.this, "This slot is booked", Toast.LENGTH_SHORT).show();
                } else {

                    slot2.setBackground(getResources().getDrawable(R.drawable.button2));
                    if (slotS1.equals("true")) {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS3.equals("true")) {
                        slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot3.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS4.equals("true")) {
                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot4.setBackground(getResources().getDrawable(R.drawable.button1));
                    }

                    selectedSlot = "11-12";
                    slotText.setText("You have selected " + selectedSlot);
                }
            }
        });
        slot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slotS3.equals("true")) {
                    Toast.makeText(BookAppointment.this, "This slot is booked", Toast.LENGTH_SHORT).show();
                } else {

                    slot3.setBackground(getResources().getDrawable(R.drawable.button2));
                    if (slotS2.equals("true")) {
                        slot2.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot2.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS1.equals("true")) {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS4.equals("true")) {
                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot4.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    selectedSlot = "07-08";
                    slotText.setText("You have selected " + selectedSlot);
                }
            }
        });
        slot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (slotS4.equals("true")) {
                    Toast.makeText(BookAppointment.this, "This slot is booked", Toast.LENGTH_SHORT).show();
                } else {

                    slot4.setBackground(getResources().getDrawable(R.drawable.button2));
                    if (slotS2.equals("true")) {
                        slot2.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot2.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS3.equals("true")) {
                        slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot3.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    if (slotS1.equals("true")) {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button3));
                    } else {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button1));
                    }
                    selectedSlot = "08-09";
                    slotText.setText("You have selected " + selectedSlot);
                }

            }
        });
        //Toast.makeText(this, ""+docNo, Toast.LENGTH_SHORT).show();
        reference.child(docNo).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    name = snapshot.child("Name").getValue().toString();
                    degree = snapshot.child("Degree").getValue().toString();
                    address = snapshot.child("Caddress").getValue().toString();
                    clinicName = snapshot.child("CName").getValue().toString();
                    profileUrl = snapshot.child("purl").getValue().toString();

                    dName.setText(name);
                    dDegree.setText(degree);
                    dclinic.setText(clinicName);
                    dAddress.setText(address);
                    Picasso.get().load(profileUrl).into(picture);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final DialogFragment dialogFragment = new DatePickerDialogTheme4();

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutSlot.setVisibility(View.INVISIBLE);
                dialogFragment.show(getSupportFragmentManager(), "theme 4");
            }
        });
    }

    public void bookApt(View view) {

        String tempDate, tempSlot;
        tempDate = selectDate.getText().toString();
        tempSlot = selectedSlot;

        if (tempDate.equals("Eg. 01-01-2001")) {
            Toast.makeText(this, "select date first", Toast.LENGTH_SHORT).show();
        } else if (tempSlot.equals("")) {
            Toast.makeText(this, "slot first", Toast.LENGTH_SHORT).show();
        } else {
            final uploadDoctor upload = new uploadDoctor(clinicName,address,degree, name,tempDate,tempSlot);
            reference.child(docNo).child("appointments").child(tempDate).child(tempSlot).child("User").setValue(userID).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    reference2.child(String.valueOf(totalAppointmentCount + 1)).setValue(upload).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(BookAppointment.this, "Appointment booked successfully..", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(BookAppointment.this, appHome.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(BookAppointment.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        }
    }

    public void checkAvailableSlot(View view) {
        final String tempDate;
        tempDate = selectDate.getText().toString();
        if (tempDate.equals("Eg. 01-01-2001")) {
            Toast.makeText(this, "select date first", Toast.LENGTH_SHORT).show();
        } else {
            reference.child(docNo).child("appointments").child(tempDate).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        reference.child(docNo).child("appointments").child(tempDate).child("10-11").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    slot1.setBackground(getResources().getDrawable(R.drawable.button3));
                                    slotS1 = "true";
                                    reference.child(docNo).child("appointments").child(tempDate).child("11-12").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                slot2.setBackground(getResources().getDrawable(R.drawable.button3));
                                                slotS2 = "true";
                                                reference.child(docNo).child("appointments").child(tempDate).child("07-08").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                            slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                                                            slotS3 = "true";
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        } else {
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            } else {
                                                reference.child(docNo).child("appointments").child(tempDate).child("07-08").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                            slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                                                            slotS3 = "true";
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        } else {
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                } else {
                                    reference.child(docNo).child("appointments").child(tempDate).child("11-12").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if (snapshot.exists()) {
                                                slot2.setBackground(getResources().getDrawable(R.drawable.button3));
                                                slotS2 = "true";
                                                reference.child(docNo).child("appointments").child(tempDate).child("07-08").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                            slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                                                            slotS3 = "true";
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        } else {
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            } else {
                                                reference.child(docNo).child("appointments").child(tempDate).child("07-08").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        if (snapshot.exists()) {
                                                            slot3.setBackground(getResources().getDrawable(R.drawable.button3));
                                                            slotS3 = "true";
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        } else {
                                                            reference.child(docNo).child("appointments").child(tempDate).child("08-09").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                @Override
                                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                    if (snapshot.exists()) {
                                                                        slot4.setBackground(getResources().getDrawable(R.drawable.button3));
                                                                        slotS4 = "true";
                                                                    }
                                                                }

                                                                @Override
                                                                public void onCancelled(@NonNull DatabaseError error) {

                                                                }
                                                            });
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        layoutSlot.setVisibility(View.VISIBLE);
                    } else {
                        slot1.setBackground(getResources().getDrawable(R.drawable.button1));
                        slot2.setBackground(getResources().getDrawable(R.drawable.button1));
                        slot3.setBackground(getResources().getDrawable(R.drawable.button1));
                        slot4.setBackground(getResources().getDrawable(R.drawable.button1));
                        slotS1 = "false";
                        slotS2 = "false";
                        slotS3 = "false";
                        slotS4 = "false";
                        layoutSlot.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    public static class DatePickerDialogTheme4 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        String date;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
            //these three lines are used to for cancle set previous dates
            calendar.add(Calendar.DATE, 0);
            Date newDate = calendar.getTime();
            //datePickerDialog.getDatePicker().setMinDate(newDate.getTime() - (newDate.getTime() % (24 * 60 * 60 * 1000)));
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            //here it ends
            long now = System.currentTimeMillis() - 1000;
            datePickerDialog.getDatePicker().setMaxDate(now + (1000 * 60 * 60 * 24 * 1)); //for maximum 2 days
            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            int month2 = month + 1;
            String formattedMonth = "" + month2;
            String formattedDayOfMonth = "" + day;

            if (month2 < 10) {

                formattedMonth = "0" + month2;
            }
            if (day < 10) {

                formattedDayOfMonth = "0" + day;
            }
            TextView textView = getActivity().findViewById(R.id.dateAppointment);
            textView.setText(formattedDayOfMonth + "-" + formattedMonth + "-" + year);
            date = textView.getText().toString().trim();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}