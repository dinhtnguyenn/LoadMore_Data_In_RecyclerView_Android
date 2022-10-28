package com.dinhnt.loadmoredatainrecyclerviewandroid.models;

public class Product {
    private String masp;
    private String tensp;
    private String dvt;
    private String nuocsx;
    private int gia;

    public Product(String masp, String tensp, String dvt, String nuocsx, int gia) {
        this.masp = masp;
        this.tensp = tensp;
        this.dvt = dvt;
        this.nuocsx = nuocsx;
        this.gia = gia;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getNuocsx() {
        return nuocsx;
    }

    public void setNuocsx(String nuocsx) {
        this.nuocsx = nuocsx;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
