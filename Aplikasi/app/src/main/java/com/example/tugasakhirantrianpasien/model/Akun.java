package com.example.tugasakhirantrianpasien.model;

public class Akun {
    private String nama;
    private String tgl_lahir;
    private String nik;
    private String alamat;
    private String email;
    private String password;
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_lahir() {

        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Akun(){

    }

    public Akun(String nik, String nama, String tgl_lahir, String alamat, String email, String password, String level) {
        this.nik = nik;
        this.nama = nama;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
        this.level = level;
    }

}
