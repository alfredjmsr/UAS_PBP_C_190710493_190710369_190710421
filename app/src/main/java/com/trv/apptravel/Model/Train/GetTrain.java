package com.trv.apptravel.Model.Train;

import com.google.gson.annotations.SerializedName;

public class GetTrain {
    @SerializedName("id_train")
    private String idtrain;
    @SerializedName("from_train")
    private String fromtrain;
    @SerializedName("to_train")
    private String totrain;
    @SerializedName("fromtime_train")
    private String fromtimetrain;
    @SerializedName("totime_train")
    private String totimetrain;
    @SerializedName("class_train")
    private String classtrain;
    @SerializedName("price_train")
    private String pricetrain;
    @SerializedName("name_train")
    private String nametrain;

    public GetTrain(String idtrain, String fromtrain, String totrain, String fromtimetrain, String totimetrain, String classtrain, String pricetrain, String nametrain) {
        this.idtrain = idtrain;
        this.fromtrain = fromtrain;
        this.totrain = totrain;
        this.fromtimetrain = fromtimetrain;
        this.totimetrain = totimetrain;
        this.classtrain = classtrain;
        this.pricetrain = pricetrain;
        this.nametrain = nametrain;
    }

    public String getIdtrain() {
        return idtrain;
    }

    public void setIdtrain(String idtrain) {
        this.idtrain = idtrain;
    }

    public String getFromtrain() {
        return fromtrain;
    }

    public void setFromtrain(String fromtrain) {
        this.fromtrain = fromtrain;
    }

    public String getTotrain() {
        return totrain;
    }

    public void setTotrain(String totrain) {
        this.totrain = totrain;
    }

    public String getFromtimetrain() {
        return fromtimetrain;
    }

    public void setFromtimetrain(String fromtimetrain) {
        this.fromtimetrain = fromtimetrain;
    }

    public String getTotimetrain() {
        return totimetrain;
    }

    public void setTotimetrain(String totimetrain) {
        this.totimetrain = totimetrain;
    }

    public String getClasstrain() {
        return classtrain;
    }

    public void setClasstrain(String classtrain) {
        this.classtrain = classtrain;
    }

    public String getPricetrain() {
        return pricetrain;
    }

    public void setPricetrain(String pricetrain) {
        this.pricetrain = pricetrain;
    }

    public String getNametrain() {
        return nametrain;
    }

    public void setNametrain(String nametrain) {
        this.nametrain = nametrain;
    }
}
