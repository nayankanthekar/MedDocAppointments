package com.example.medappointment;

public class User {
    public String name,email,address,phoneNo;

    public User() {
    }

    public User(String name,String email,String phoneNo,String address)
    {
        this.name = name;
        this.email= email;
        this.phoneNo =phoneNo;
        this.address = address;
    }
}
