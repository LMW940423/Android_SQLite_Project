package com.android.sqlite_crud;

public class Student {

    //Field
    private int sno;
    private String userid;
    private String username;
    private String userpw;
    private String usermajor;
    private String usertel;

    //Constructor


    public Student(int sno, String userid, String username, String userpw, String usermajor, String usertel) {
        this.sno = sno;
        this.userid = userid;
        this.username = username;
        this.userpw = userpw;
        this.usermajor = usermajor;
        this.usertel = usertel;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpw() {
        return userpw;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public String getUsermajor() {
        return usermajor;
    }

    public void setUsermajor(String usermajor) {
        this.usermajor = usermajor;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }
}
