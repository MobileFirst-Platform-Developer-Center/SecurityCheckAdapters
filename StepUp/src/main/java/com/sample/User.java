package com.sample;

public class User {
    public String name;
    public String displayName;
    public String password;
    public String pincode;

    public User(String name, String displayName, String password, String pincode){
        this.name = name;
        this.displayName = displayName;
        this.password = password;
        this.pincode = pincode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
