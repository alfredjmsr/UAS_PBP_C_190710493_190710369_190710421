package com.trv.apptravel.Model.Laporan;

import com.google.gson.annotations.SerializedName;

public class Laporan {
    @SerializedName("jumlah_transaksi")
    private String jumlah_transaksi;
    @SerializedName("total_transaksi")
    private String total_transaksi;
    @SerializedName("avg_transaksi")
    private String avg_transaksi;

    public Laporan(String jumlah_transaksi, String total_transaksi, String avg_transaksi) {
        this.jumlah_transaksi = jumlah_transaksi;
        this.total_transaksi = total_transaksi;
        this.avg_transaksi = avg_transaksi;
    }

    public String getJumlah_transaksi() {
        return jumlah_transaksi;
    }

    public void setJumlah_transaksi(String jumlah_transaksi) {
        this.jumlah_transaksi = jumlah_transaksi;
    }

    public String getTotal_transaksi() {
        return total_transaksi;
    }

    public void setTotal_transaksi(String total_transaksi) {
        this.total_transaksi = total_transaksi;
    }

    public String getAvg_transaksi() {
        return avg_transaksi;
    }

    public void setAvg_transaksi(String avg_transaksi) {
        this.avg_transaksi = avg_transaksi;
    }
}
