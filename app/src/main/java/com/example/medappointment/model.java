package com.example.medappointment;

import android.location.Address;

public class model {


    String Name,Degree,Caddress,purl;

    public model() {

    }


    public model(String Name,  String Degree, String Caddress, String purl) {
        this.Name = Name;
        //this.clinicName = clinicName;String clinicName,clinicName,
        this.Degree = Degree;
        this.Caddress = Caddress;
        this.purl = purl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

//    public String getClinicName() {
//        return clinicName;
//    }
//
//    public void setClinicName(String clinicName) {
//        this.clinicName = clinicName;
//    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String Degree) {
        this.Degree = Degree;
    }

    public String getCaddress() {
        return Caddress;
    }

    public void setCaddress(String Caddress) {
        this.Caddress = Caddress;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
