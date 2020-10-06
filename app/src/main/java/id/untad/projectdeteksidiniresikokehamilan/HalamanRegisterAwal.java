package id.untad.projectdeteksidiniresikokehamilan;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.RegisterHelper;

public class HalamanRegisterAwal extends AppCompatActivity implements DatePickerFragment.DialogDateListener {
    public static final String EXTRA_DATE = "extra_date";
    public static final String SHARED_DATE = "sharedDate";
    public static final String KEYA_DATE = "keyDate";
    public static final String EXTRA_MONTH = "extra_month";
    public static final String SHARED_MONTH = "sharedmonth";
    public static final String KEY_MONTH = "keymonth";
    public static final String EXTRA_YEAR = "extra_year";
    public static final String SHARED_YEAR = "sharedYear";
    public static final String KEY_YEAR = "keyyear";
    public static final String EXTRA_NAMA = "extraScore";
    final String DATE_PICKER_TAG1 = "DatePicker1";
    final String DATE_PICKER_TAG2 = "DatePicker2";
    private EditText nama_ibu, tanggal_lahir, umur, alamat, nama_suami, no_hp_ibu, no_hp_suami, perkiraan_hamil;
    private String SETNAMAIBU, SETTANGGALLAHIR, SETUMUR, SETAGAMA, SETPEKERJAANIBU, SETPENDIDIKANIBU, SETGOLONGANDARAH, SETALAMAT, SETNAMASUAMI, SETPEKERJAANSUAMI, SETPENDIDIKANSUAMI, SETNOHPIBU, SETNOHPSUAMI, SETEMAIL;
    private Spinner agama, pekerjaan_ibu, pendidikan_ibu, golongan_darah, pekerjaan_suami, pendidikan_suami;
    private RegisterHelper reghelp;
    private Button btnsimpan;
    private TextView vb;
    private boolean bol;
    private int register = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_halaman_register_awal);
        nama_ibu = findViewById(R.id.edt_namaibuupdate);
        tanggal_lahir = findViewById(R.id.edt_tanggal_lahir_update);
        umur = findViewById(R.id.edt_umur_update);
        agama = findViewById(R.id.spin_agama_update);
        tanggal_lahir = findViewById(R.id.edt_tanggal_lahir_update);
        pekerjaan_ibu = findViewById(R.id.spin_pekerjaan_ibu_update);
        pendidikan_ibu = findViewById(R.id.spin_pendidikan_ibu_update);
        golongan_darah = findViewById(R.id.spin_golongan_darah_update);
        alamat = findViewById(R.id.edt_alamat_update);
        nama_suami = findViewById(R.id.edt_nama_suami_update);
        pekerjaan_suami = findViewById(R.id.spin_pekerjaan_suami_update);
        pendidikan_suami = findViewById(R.id.spin_pendidikan_suami_update);
        no_hp_ibu = findViewById(R.id.edt_no_hp_ibu_update);
        no_hp_suami = findViewById(R.id.edt_no_hp_suami_upadte);
        reghelp = new RegisterHelper(getBaseContext());
        btnsimpan = findViewById(R.id.btn_submit_update);
        RegisterHelper dbHelper = new RegisterHelper(this);
        bol = dbHelper.check();
        //  dbHelper.getAllQuestions();

        tanggal_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG1);
            }
        });



        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama_ibu.getText().toString().length() == 0) {
                    nama_ibu.setError("Mohon Di Isi");
                } else if (tanggal_lahir.getText().toString().length() == 0) {
                    tanggal_lahir.setError("Mohon Di Isi");
                } else if (umur.getText().toString().length() == 0) {
                    umur.setError("Mohon Di Isi");
                } else if (alamat.getText().toString().length() == 0) {
                    alamat.setError("Mohon Di Isi");
                } else if (nama_suami.getText().toString().length() == 0) {
                    nama_suami.setError("Mohon Di Isi");
                } else if (no_hp_ibu.getText().toString().length() == 0) {
                    no_hp_ibu.setError("Mohon Di Isi");
                } else if (no_hp_suami.getText().toString().length() == 0) {
                    no_hp_suami.setError("Mohon Di Isi");
                } else {
                    setData();
                    saveData();
                }
            }
        });
    }


    private void setData() {
        SETNAMAIBU = nama_ibu.getText().toString();
        SETTANGGALLAHIR = tanggal_lahir.getText().toString();
        SETUMUR = umur.getText().toString();
        SETAGAMA = agama.getSelectedItem().toString();
        SETPEKERJAANIBU = pekerjaan_ibu.getSelectedItem().toString();
        SETPENDIDIKANIBU = pendidikan_ibu.getSelectedItem().toString();
        SETGOLONGANDARAH = golongan_darah.getSelectedItem().toString();
        SETALAMAT = alamat.getText().toString();
        SETNAMASUAMI = nama_suami.getText().toString();
        SETPEKERJAANSUAMI = pekerjaan_suami.getSelectedItem().toString();
        SETPENDIDIKANSUAMI = pendidikan_suami.getSelectedItem().toString();
        SETNOHPIBU = no_hp_ibu.getText().toString();
        SETNOHPSUAMI = no_hp_suami.getText().toString();
    }


    //Berisi Statement-Statement Untuk Menyimpan Data Pada Database
    private void saveData() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle("Apakah Data Yang Anda Masukkan Sudah Benar ?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Mendapatkan Repository dengan Mode Menulis
                        SQLiteDatabase create = reghelp.getWritableDatabase();
                        //Membuat Map Baru, Yang Berisi Nama Kolom dan Data Yang Ingin Dimasukan
                        ContentValues values = new ContentValues();
                        values.put(QuizContract.Register.NAMA_IBU, SETNAMAIBU);
                        values.put(QuizContract.Register.TANGGAL_LAHIR, SETTANGGALLAHIR);
                        values.put(QuizContract.Register.UMUR, SETUMUR);
                        values.put(QuizContract.Register.AGAMA, SETAGAMA);
                        values.put(QuizContract.Register.PEKERJAAN_IBU, SETPEKERJAANIBU);
                        values.put(QuizContract.Register.PENDIDIKAN_IBU, SETPENDIDIKANIBU);
                        values.put(QuizContract.Register.GOLONGAN_DARAH, SETGOLONGANDARAH);
                        values.put(QuizContract.Register.ALAMAT, SETALAMAT);
                        values.put(QuizContract.Register.NAMA_SUAMI, SETNAMASUAMI);
                        values.put(QuizContract.Register.PEKERJAAN_SUAMI, SETPEKERJAANSUAMI);
                        values.put(QuizContract.Register.PENDIDIKAN_SUAMI, SETPENDIDIKANSUAMI);
                        values.put(QuizContract.Register.NO_HP_IBU, SETNOHPIBU);
                        values.put(QuizContract.Register.NO_HP_SUAMI, SETNOHPSUAMI);
                        values.put(QuizContract.Register.EMAIL, SETEMAIL);
                        //Menambahkan Baris Baru, Berupa Data Yang Sudah Diinputkan pada Kolom didalam Database
                        create.insert(QuizContract.Register.TABLE_NAME, null, values);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra(EXTRA_NAMA, register);
                        setResult(RESULT_OK, resultIntent);
                        Toast.makeText(HalamanRegisterAwal.this, "Data Telah Tersimpan", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // membuat alert dialog dari builder
        AlertDialog alertDialog = alertDialogBuilder.create();
        // menampilkan alert dialog
        alertDialog.show();
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MM - yyyy", Locale.getDefault());
        tanggal_lahir.setText(dateFormat.format(calendar.getTime()));
        tanggal_lahir.setFocusable(false);

        //Set Tanggal Lahir
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DATE, dayOfMonth);
        thatDay.set(Calendar.MONTH, month); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);

        //Menghitung Umur
        Calendar today = Calendar.getInstance();
        long diff = (today.get(Calendar.YEAR)) - (thatDay.get(Calendar.YEAR));
        String yearString = Long.toString(diff);
        umur.setText(yearString + " Tahun");
    }
}
