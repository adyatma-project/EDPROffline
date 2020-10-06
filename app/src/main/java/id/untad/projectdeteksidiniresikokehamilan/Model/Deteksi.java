package id.untad.projectdeteksidiniresikokehamilan.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Deteksi {
    private String pertanyaan;
    private String jawaban;

    public Deteksi() {
    }

    public Deteksi(String pertanyaan, String jawaban) {
        this.pertanyaan = pertanyaan;
        this.jawaban = jawaban;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}