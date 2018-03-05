package com.erickogi14gmail.assettrack.Data;

/**
 * Created by Eric on 1/5/2018.
 */

public class UserModel {
    private String userName;
    private String photo;
    private String email;
    private String phoneNumber;
    private int userId;
    private String firstName;
    private String lastName;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
