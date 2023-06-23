package com.example.projekpab;

import android.os.Parcel;
import android.os.Parcelable;

public class Unggah implements Parcelable {
    private String id;
    private String nama;
    private String alamat;
    private String telepon;
    private String motor;
    private String jenis;
    private String servis;
    private String user_id;
    private String created_date;
    private String modified_date;
    private String username;

    protected Unggah(Parcel in) {
        id = in.readString();
        nama = in.readString();
        alamat = in.readString();
        telepon = in.readString();
        motor = in.readString();
        jenis = in.readString();
        servis = in.readString();
        user_id = in.readString();
        created_date = in.readString();
        modified_date = in.readString();
        username = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeString(alamat);
        dest.writeString(telepon);
        dest.writeString(motor);
        dest.writeString(jenis);
        dest.writeString(servis);
        dest.writeString(user_id);
        dest.writeString(created_date);
        dest.writeString(modified_date);
        dest.writeString(username);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Unggah> CREATOR = new Creator<Unggah>() {
        @Override
        public Unggah createFromParcel(Parcel in) {
            return new Unggah(in);
        }

        @Override
        public Unggah[] newArray(int size) {
            return new Unggah[size];
        }
    };
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getServis() {
        return servis;
    }

    public void setServis(String servis) {
        this.servis = servis;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getModified_date() {
        return modified_date;
    }

    public void setModified_date(String modified_date) {
        this.modified_date = modified_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
