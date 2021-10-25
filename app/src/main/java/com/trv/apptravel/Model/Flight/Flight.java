package com.trv.apptravel.Model.Flight;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Flight {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<GetFlight> getFlights;
    @SerializedName("message")
    String message;

    public Flight(String status, List<GetFlight> getFlights, String message) {
        this.status = status;
        this.getFlights = getFlights;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetFlight> getGetFlights() {
        return getFlights;
    }

    public void setGetFlights(List<GetFlight> getFlights) {
        this.getFlights = getFlights;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
