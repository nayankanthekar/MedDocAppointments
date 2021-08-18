package com.example.medappointment;

public class uploadDoctor {
    String CName, Caddress, Degree, Name, purl, TimeSlot;

    public uploadDoctor(String CName, String caddress, String degree, String name, String purl, String timeSlot) {
        this.CName = CName;
        Caddress = caddress;
        Degree = degree;
        Name = name;
        TimeSlot = timeSlot;
        this.purl = purl;
    }

    public uploadDoctor() {
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCaddress() {
        return Caddress;
    }

    public void setCaddress(String caddress) {
        Caddress = caddress;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getTimeSlot() {
        return TimeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        TimeSlot = timeSlot;
    }
}
