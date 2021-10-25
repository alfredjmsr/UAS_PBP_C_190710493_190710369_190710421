package com.trv.apptravel.Model.Laporan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LaporanList {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Laporan> laporans;
    @SerializedName("message")
    String message;

    public LaporanList(String status, List<Laporan> laporans, String message) {
        this.status = status;
        this.laporans = laporans;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Laporan> getLaporans() {
        return laporans;
    }

    public void setLaporans(List<Laporan> laporans) {
        this.laporans = laporans;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
