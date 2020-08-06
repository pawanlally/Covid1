package com.example.covid;

public class user {
    public String name,username,email,password,phoneNo;
    public user(){

    }
    public user(String name, String username, String email, String phoneNo, String password){
        this.name=name;
        this.username=username;
        this.email=email;
        this.phoneNo=phoneNo;
        this.password=password;
    }
    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(){
        this.username=username;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(){
        this.email=email;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    public void setPhoneNo(){
        this.phoneNo=phoneNo;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password=password;
    }

}
