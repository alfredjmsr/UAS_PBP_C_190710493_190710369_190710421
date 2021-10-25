package com.trv.apptravel.Model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginUsers {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("nama_user")
    private String nama_user;
    @SerializedName("password_user")
    private String password_user;
    @SerializedName("email_user")
    private String email_user;
    @SerializedName("role_user")
    private String role_user;
    @SerializedName("phonenumber_user")
    private String phonenumber_user;
    @SerializedName("birthdate_user")
    private String birthdate_user;
    @SerializedName("image_user")
    private String image_user;

    public LoginUsers(String id_user, String nama_user, String password_user, String email_user, String role_user, String phonenumber_user, String birthdate_user, String image_user) {
        this.id_user = id_user;
        this.nama_user = nama_user;
        this.password_user = password_user;
        this.email_user = email_user;
        this.role_user = role_user;
        this.phonenumber_user = phonenumber_user;
        this.birthdate_user = birthdate_user;
        this.image_user = image_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getRole_user() {
        return role_user;
    }

    public void setRole_user(String role_user) {
        this.role_user = role_user;
    }

    public String getPhonenumber_user() {
        return phonenumber_user;
    }

    public void setPhonenumber_user(String phonenumber_user) {
        this.phonenumber_user = phonenumber_user;
    }

    public String getBirthdate_user() {
        return birthdate_user;
    }

    public void setBirthdate_user(String birthdate_user) {
        this.birthdate_user = birthdate_user;
    }

    public String getImage_user() {
        return image_user;
    }

    public void setImage_user(String image_user) {
        this.image_user = image_user;
    }
}
