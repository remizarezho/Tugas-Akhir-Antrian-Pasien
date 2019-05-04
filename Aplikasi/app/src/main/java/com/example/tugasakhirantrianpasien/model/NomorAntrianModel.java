package com.example.tugasakhirantrianpasien.model;

public class NomorAntrianModel  {
    String id;
    Detail detail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public void setDetail(String nik, String nomor, String nama, String poli, String waktu) {
        this.detail = new Detail(nik, nomor, nama, poli, waktu);
    }



    class Detail{

        Detail(String nik, String nomor, String nama, String poli, String waktu){
            this.nik = nik;
            this.nomor = nomor;
            this.nama = nama;
            this.poli = poli;
            this.waktu = waktu;
        }

        private String nik;
        private String nomor;
        private String nama;
        private String poli;
        private String waktu;

        public String getNik() {
            return nik;
        }

        public void setNik(String nik) {
            this.nik = nik;
        }

        public String getNomor() {
            return nomor;
        }

        public void setNomor(String nomor) {
            this.nomor = nomor;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getPoli() {
            return poli;
        }

        public void setPoli(String poli) {
            this.poli = poli;
        }

        public String getWaktu() {
            return waktu;
        }

        public void setWaktu(String waktu) {
            this.waktu = waktu;
        }

    }
}
