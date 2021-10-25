package com.trv.apptravel.Model.Order;

import com.google.gson.annotations.SerializedName;

public class GetOrder {
    @SerializedName("id_order")
    private String id_order;
    @SerializedName("firstname_order")
    private String firstname_order;
    @SerializedName("lastname_order")
    private String lastname_order;
    @SerializedName("email_order")
    private String email_order;
    @SerializedName("phonenumber_order")
    private String phonenumber_order;
    @SerializedName("fromflight_order")
    private String fromflight_order;
    @SerializedName("toflight_order")
    private String toflight_order;
    @SerializedName("fromflighttime_order")
    private String fromflighttime_order;
    @SerializedName("toflighttime_order")
    private String toflighttime_order;
    @SerializedName("nameflight_order")
    private String nameflight_order;
    @SerializedName("iduser_order")
    private String iduser_order;
    @SerializedName("price_order")
    private String price_order;
    @SerializedName("address_order")
    private String address_order;

    public GetOrder(String id_order, String firstname_order, String lastname_order, String email_order, String phonenumber_order, String fromflight_order, String toflight_order, String fromflighttime_order, String toflighttime_order, String nameflight_order, String iduser_order, String price_order, String address_order) {
        this.id_order = id_order;
        this.firstname_order = firstname_order;
        this.lastname_order = lastname_order;
        this.email_order = email_order;
        this.phonenumber_order = phonenumber_order;
        this.fromflight_order = fromflight_order;
        this.toflight_order = toflight_order;
        this.fromflighttime_order = fromflighttime_order;
        this.toflighttime_order = toflighttime_order;
        this.nameflight_order = nameflight_order;
        this.iduser_order = iduser_order;
        this.price_order = price_order;
        this.address_order = address_order;
    }

    public String getId_order() {
        return id_order;
    }

    public void setId_order(String id_order) {
        this.id_order = id_order;
    }

    public String getFirstname_order() {
        return firstname_order;
    }

    public void setFirstname_order(String firstname_order) {
        this.firstname_order = firstname_order;
    }

    public String getLastname_order() {
        return lastname_order;
    }

    public void setLastname_order(String lastname_order) {
        this.lastname_order = lastname_order;
    }

    public String getEmail_order() {
        return email_order;
    }

    public void setEmail_order(String email_order) {
        this.email_order = email_order;
    }

    public String getPhonenumber_order() {
        return phonenumber_order;
    }

    public void setPhonenumber_order(String phonenumber_order) {
        this.phonenumber_order = phonenumber_order;
    }

    public String getFromflight_order() {
        return fromflight_order;
    }

    public void setFromflight_order(String fromflight_order) {
        this.fromflight_order = fromflight_order;
    }

    public String getToflight_order() {
        return toflight_order;
    }

    public void setToflight_order(String toflight_order) {
        this.toflight_order = toflight_order;
    }

    public String getFromflighttime_order() {
        return fromflighttime_order;
    }

    public void setFromflighttime_order(String fromflighttime_order) {
        this.fromflighttime_order = fromflighttime_order;
    }

    public String getToflighttime_order() {
        return toflighttime_order;
    }

    public void setToflighttime_order(String toflighttime_order) {
        this.toflighttime_order = toflighttime_order;
    }

    public String getNameflight_order() {
        return nameflight_order;
    }

    public void setNameflight_order(String nameflight_order) {
        this.nameflight_order = nameflight_order;
    }

    public String getIduser_order() {
        return iduser_order;
    }

    public void setIduser_order(String iduser_order) {
        this.iduser_order = iduser_order;
    }

    public String getPrice_order() {
        return price_order;
    }

    public void setPrice_order(String price_order) {
        this.price_order = price_order;
    }

    public String getAddress_order() {
        return address_order;
    }

    public void setAddress_order(String address_order) {
        this.address_order = address_order;
    }
}
