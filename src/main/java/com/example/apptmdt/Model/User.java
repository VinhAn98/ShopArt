package com.example.apptmdt.Model;

public class User  {

    private String email,name,id_user,phone;

    public User(String email, String name, String id_user, String phone) {
        this.email = email;
        this.name = name;
        this.id_user = id_user;
        this.phone = phone;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
