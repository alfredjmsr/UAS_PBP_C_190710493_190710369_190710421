package com.trv.apptravel.Model.Flight;

import com.google.gson.annotations.SerializedName;

public class GetFlight {
    @SerializedName("id_flight")
    private String idFlight;
    @SerializedName("from_flight")
    private String fromFlight;
    @SerializedName("to_flight")
    private String toFlight;
    @SerializedName("fromtime_flight")
    private String fromtimeFlight;
    @SerializedName("totime_flight")
    private String totimeFlight;
    @SerializedName("class_flight")
    private String classFlight;
    @SerializedName("price_flight")
    private String priceFlight;
    @SerializedName("name_flight")
    private String nameFlight;

    public GetFlight(String idFlight, String fromFlight, String toFlight, String fromtimeFlight, String totimeFlight, String classFlight, String priceFlight, String nameFlight) {
        this.idFlight = idFlight;
        this.fromFlight = fromFlight;
        this.toFlight = toFlight;
        this.fromtimeFlight = fromtimeFlight;
        this.totimeFlight = totimeFlight;
        this.classFlight = classFlight;
        this.priceFlight = priceFlight;
        this.nameFlight = nameFlight;
    }

    public String getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(String idFlight) {
        this.idFlight = idFlight;
    }

    public String getFromFlight() {
        return fromFlight;
    }

    public void setFromFlight(String fromFlight) {
        this.fromFlight = fromFlight;
    }

    public String getToFlight() {
        return toFlight;
    }

    public void setToFlight(String toFlight) {
        this.toFlight = toFlight;
    }

    public String getFromtimeFlight() {
        return fromtimeFlight;
    }

    public void setFromtimeFlight(String fromtimeFlight) {
        this.fromtimeFlight = fromtimeFlight;
    }

    public String getTotimeFlight() {
        return totimeFlight;
    }

    public void setTotimeFlight(String totimeFlight) {
        this.totimeFlight = totimeFlight;
    }

    public String getClassFlight() {
        return classFlight;
    }

    public void setClassFlight(String classFlight) {
        this.classFlight = classFlight;
    }

    public String getPriceFlight() {
        return priceFlight;
    }

    public void setPriceFlight(String priceFlight) {
        this.priceFlight = priceFlight;
    }

    public String getNameFlight() {
        return nameFlight;
    }

    public void setNameFlight(String nameFlight) {
        this.nameFlight = nameFlight;
    }
}
