package id.untad.projectdeteksidiniresikokehamilan.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Register implements Parcelable {
    private String nama_ibu;
    private String tanggal_lahir;
    private String umur;
    private String agama;
    private String pekerjaan_ibu;
    private String pendidikan_ibu;
    private String golongan_darah;
    private String alamat;
    private String nama_suami;
    private String pekerjaan_suami;
    private String pendidikan_suami;
    private String no_hp_ibu;
    private String no_hp_suami;
    private String email;



    public Register() {
    }

    public Register(String nama_ibu, String tanggal_lahir, String umur, String agama, String pekerjaan_ibu, String pendidikan_ibu, String golongan_darah, String alamat, String nama_suami, String pekerjaan_suami, String pendidikan_suami, String no_hp_ibu, String no_hp_suami, String email) {
        this.nama_ibu = nama_ibu;
        this.tanggal_lahir = tanggal_lahir;
        this.umur = umur;
        this.agama = agama;
        this.pekerjaan_ibu = pekerjaan_ibu;
        this.pendidikan_ibu = pendidikan_ibu;
        this.golongan_darah = golongan_darah;
        this.alamat = alamat;
        this.nama_suami = nama_suami;
        this.pekerjaan_suami = pekerjaan_suami;
        this.pendidikan_suami = pendidikan_suami;
        this.no_hp_ibu = no_hp_ibu;
        this.no_hp_suami = no_hp_suami;
        this.email = email;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getPekerjaan_ibu() {
        return pekerjaan_ibu;
    }

    public void setPekerjaan_ibu(String pekerjaan_ibu) {
        this.pekerjaan_ibu = pekerjaan_ibu;
    }

    public String getPendidikan_ibu() {
        return pendidikan_ibu;
    }

    public void setPendidikan_ibu(String pendidikan_ibu) {
        this.pendidikan_ibu = pendidikan_ibu;
    }

    public String getGolongan_darah() {
        return golongan_darah;
    }

    public void setGolongan_darah(String golongan_darah) {
        this.golongan_darah = golongan_darah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_suami() {
        return nama_suami;
    }

    public void setNama_suami(String nama_suami) {
        this.nama_suami = nama_suami;
    }

    public String getPekerjaan_suami() {
        return pekerjaan_suami;
    }

    public void setPekerjaan_suami(String pekerjaan_suami) {
        this.pekerjaan_suami = pekerjaan_suami;
    }

    public String getPendidikan_suami() {
        return pendidikan_suami;
    }

    public void setPendidikan_suami(String pendidikan_suami) {
        this.pendidikan_suami = pendidikan_suami;
    }

    public String getNo_hp_ibu() {
        return no_hp_ibu;
    }

    public void setNo_hp_ibu(String no_hp_ibu) {
        this.no_hp_ibu = no_hp_ibu;
    }

    public String getNo_hp_suami() {
        return no_hp_suami;
    }

    public void setNo_hp_suami(String no_hp_suami) {
        this.no_hp_suami = no_hp_suami;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nama_ibu);
        dest.writeString(this.tanggal_lahir);
        dest.writeString(this.umur);
        dest.writeString(this.agama);
        dest.writeString(this.pekerjaan_ibu);
        dest.writeString(this.pendidikan_ibu);
        dest.writeString(this.golongan_darah);
        dest.writeString(this.alamat);
        dest.writeString(this.nama_suami);
        dest.writeString(this.pekerjaan_suami);
        dest.writeString(this.pendidikan_suami);
        dest.writeString(this.no_hp_ibu);
        dest.writeString(this.no_hp_suami);
        dest.writeString(this.email);
    }

    protected Register(Parcel in) {
        this.nama_ibu = in.readString();
        this.tanggal_lahir = in.readString();
        this.umur = in.readString();
        this.agama = in.readString();
        this.pekerjaan_ibu = in.readString();
        this.pendidikan_ibu = in.readString();
        this.golongan_darah = in.readString();
        this.alamat = in.readString();
        this.nama_suami = in.readString();
        this.pekerjaan_suami = in.readString();
        this.pendidikan_suami = in.readString();
        this.no_hp_ibu = in.readString();
        this.no_hp_suami = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<Register> CREATOR = new Parcelable.Creator<Register>() {
        @Override
        public Register createFromParcel(Parcel source) {
            return new Register(source);
        }

        @Override
        public Register[] newArray(int size) {
            return new Register[size];
        }
    };
}