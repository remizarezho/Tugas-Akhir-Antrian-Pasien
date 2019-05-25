package com.example.tugasakhirantrianpasien.model;

public class Pelayanan {
    private String iddokter;
    private String namadokter;
    private String jampelayanan;
    private String haripelayanan;
    private String poli;

    public String getIddokter() {
        return iddokter;
    }

    public void setIddokter(String iddokter) {
        this.iddokter = iddokter;
    }

    public String getNamadokter() {
        return namadokter;
    }

    public void setNamadokter(String namadokter) {
        this.namadokter = namadokter;
    }

    public String getJampelayanan() {
        return jampelayanan;
    }

    public void setJampelayanan(String jampelayanan) {
        this.jampelayanan = jampelayanan;
    }

    public String getHaripelayanan() {
        return haripelayanan;
    }

    public void setHaripelayanan(String haripelayanan) {
        this.haripelayanan = haripelayanan;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }


    public Pelayanan(String iddokter,String namadokter, String jampelayanan, String poli,
                     String haripelayanan) {
        this.iddokter = iddokter;
        this.namadokter = namadokter;
        this.jampelayanan = jampelayanan;
        this.haripelayanan = haripelayanan;
        this.poli =poli;
    }
}
