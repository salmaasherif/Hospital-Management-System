package com.mycompany.hospital;

public class Appointment {

    String pname, dname, time, section;

    public Appointment(String pname, String dname, String time, String section) {
        this.pname = pname;
        this.dname = dname;
        this.time = time;
        this.section = section;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

}
