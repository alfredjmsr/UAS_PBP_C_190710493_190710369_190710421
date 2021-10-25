package com.trv.apptravel.Model.Train;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Train {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<GetTrain> getTrains;
    @SerializedName("message")
    String message;

    public Train(String status, List<GetTrain> getTrains, String message) {
        this.status = status;
        this.getTrains = getTrains;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<GetTrain> getGetTrains() {
        return getTrains;
    }

    public void setGetTrains(List<GetTrain> getTrains) {
        this.getTrains = getTrains;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
