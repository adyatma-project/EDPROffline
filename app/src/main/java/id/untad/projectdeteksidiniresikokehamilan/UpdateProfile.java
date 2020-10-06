package id.untad.projectdeteksidiniresikokehamilan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.RegisterHelper;
import id.untad.projectdeteksidiniresikokehamilan.Model.Register;

public class UpdateProfile extends AppCompatActivity implements DatePickerFragment.DialogDateListener {
    public static final String EXTRA_PERSON = "extra_person";
    private EditText nama_ibu_update, tanggal_lahirupdate, umurupdate, alamatupdate, nama_suamiupdate, no_hp_ibuupdate, no_hp_suamiupdate, perkiraan_hamil;
    private String  SETID, SETNAMAIBU, SETTANGGALLAHIR, SETUMUR, SETAGAMA, SETPEKERJAANIBU, SETPENDIDIKANIBU, SETGOLONGANDARAH, SETALAMAT, SETNAMASUAMI, SETPEKERJAANSUAMI, SETPENDIDIKANSUAMI, SETNOHPIBU, SETNOHPSUAMI, SETEMAIL;
    private Spinner agamaupdate, pekerjaan_ibuupdate, pendidikan_ibuupdate, golongan_darahupdate, pekerjaan_suamiupdate, pendidikan_suamiupdate;
    private RegisterHelper reghelp;
    private Button btnsimpanupdate;
    private String id ="1";
    final String DATE_PICKER_TAG1 = "DatePicker1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        nama_ibu_update = findViewById(R.id.edt_namaibuupdate);
        tanggal_lahirupdate = findViewById(R.id.edt_tanggal_lahir_update);
        umurupdate = findViewById(R.id.edt_umur_update);
        agamaupdate = findViewById(R.id.spin_agama_update);
        tanggal_lahirupdate = findViewById(R.id.edt_tanggal_lahir_update);
        pekerjaan_ibuupdate = findViewById(R.id.spin_pekerjaan_ibu_update);
        pendidikan_ibuupdate = findViewById(R.id.spin_pendidikan_ibu_update);
        golongan_darahupdate = findViewById(R.id.spin_golongan_darah_update);
        alamatupdate = findViewById(R.id.edt_alamat_update);
        nama_suamiupdate = findViewById(R.id.edt_nama_suami_update);
        pekerjaan_suamiupdate = findViewById(R.id.spin_pekerjaan_suami_update);
        pendidikan_suamiupdate = findViewById(R.id.spin_pendidikan_suami_update);
        no_hp_ibuupdate = findViewById(R.id.edt_no_hp_ibu_update);
        no_hp_suamiupdate = findViewById(R.id.edt_no_hp_suami_upadte);
        btnsimpanupdate = findViewById(R.id.btn_submit_update);
        reghelp = new RegisterHelper(getBaseContext());
        RegisterHelper dbHelper = new RegisterHelper(this);
        tanggal_lahirupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG1);
            }
        });

        Register person = getIntent().getParcelableExtra(EXTRA_PERSON);
        String getnamaibu = person.getNama_ibu();
        String gettanggallahir = person.getTanggal_lahir();
        String getumur = person.getUmur();
        String getagama = person.getAgama();
        String getpekerjaanibu = person.getPekerjaan_ibu();
        String getpendidikanibu = person.getPendidikan_ibu();
        String getgolongandarah = person.getGolongan_darah();
        String getalamat = person.getAlamat();
        String getnamasuami = person.getNama_suami();
        String getpekerjaansuami = person.getPekerjaan_suami();
        String getpendidikansuami = person.getPendidikan_suami();
        String getnohpibu = person.getNo_hp_ibu();
        String getnohpsuami = person.getNo_hp_suami();

        ArrayAdapter <CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.agama, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter <CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.pekerjaan_ibu, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter <CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.pendidikan_ibu, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter <CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.golongandarah, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter <CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.pekerjaan_suami, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter <CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.pendidikan_suami, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        agamaupdate.setAdapter(adapter1);
        pekerjaan_ibuupdate.setAdapter(adapter2);
        pendidikan_ibuupdate.setAdapter(adapter3);
        golongan_darahupdate.setAdapter(adapter4);
        pekerjaan_suamiupdate.setAdapter(adapter5);
        pendidikan_suamiupdate.setAdapter(adapter6);

        if (getagama != null) {
            int spinnerPosition = adapter1.getPosition(getagama);
            agamaupdate.setSelection(spinnerPosition);
        }
        if (getpekerjaanibu != null) {
            int spinnerPosition = adapter2.getPosition(getpekerjaanibu);
            pekerjaan_ibuupdate.setSelection(spinnerPosition);
        }
        if (getpendidikanibu != null) {
            int spinnerPosition = adapter3.getPosition(getpendidikanibu);
            pendidikan_ibuupdate.setSelection(spinnerPosition);
        }
        if (getgolongandarah != null) {
            int spinnerPosition = adapter4.getPosition(getgolongandarah);
            golongan_darahupdate.setSelection(spinnerPosition);
        }
        if (getpekerjaansuami != null) {
            int spinnerPosition = adapter5.getPosition(getpekerjaansuami);
            pekerjaan_suamiupdate.setSelection(spinnerPosition);
        }
        if (getpendidikansuami != null) {
            int spinnerPosition = adapter6.getPosition(getpendidikansuami);
            pendidikan_suamiupdate.setSelection(spinnerPosition);
        }

        nama_ibu_update.setText(getnamaibu);
        tanggal_lahirupdate.setText(gettanggallahir);
        umurupdate.setText(getumur);
        alamatupdate.setText(getalamat);
        nama_suamiupdate.setText(getnamasuami);
        no_hp_ibuupdate.setText(getnohpibu);
        no_hp_suamiupdate.setText(getnohpsuami);



        btnsimpanupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama_ibu_update.getText().toString().length() == 0) {
                    nama_ibu_update.setError("Mohon Di Isi");
                } else if (tanggal_lahirupdate.getText().toString().length() == 0) {
                    tanggal_lahirupdate.setError("Mohon Di Isi");
                } else if (umurupdate.getText().toString().length() == 0) {
                    umurupdate.setError("Mohon Di Isi");
                } else if (alamatupdate.getText().toString().length() == 0) {
                    alamatupdate.setError("Mohon Di Isi");
                } else if (nama_suamiupdate.getText().toString().length() == 0) {
                    nama_suamiupdate.setError("Mohon Di Isi");
                } else if (no_hp_ibuupdate.getText().toString().length() == 0) {
                    no_hp_ibuupdate.setError("Mohon Di Isi");
                } else if (no_hp_suamiupdate.getText().toString().length() == 0) {
                    no_hp_suamiupdate.setError("Mohon Di Isi");
                } else {
                    setData();
                    saveData();
                }
            }
        });
    }


    private void setData() {
        SETID = QuizContract.Register._ID;
        SETNAMAIBU = nama_ibu_update.getText().toString();
        SETTANGGALLAHIR = tanggal_lahirupdate.getText().toString();
        SETUMUR = umurupdate.getText().toString();
        SETAGAMA = agamaupdate.getSelectedItem().toString();
        SETPEKERJAANIBU = pekerjaan_ibuupdate.getSelectedItem().toString();
        SETPENDIDIKANIBU = pendidikan_ibuupdate.getSelectedItem().toString();
        SETGOLONGANDARAH = golongan_darahupdate.getSelectedItem().toString();
        SETALAMAT = alamatupdate.getText().toString();
        SETNAMASUAMI = nama_suamiupdate.getText().toString();
        SETPEKERJAANSUAMI = pekerjaan_suamiupdate.getSelectedItem().toString();
        SETPENDIDIKANSUAMI = pendidikan_suamiupdate.getSelectedItem().toString();
        SETNOHPIBU = no_hp_ibuupdate.getText().toString();
        SETNOHPSUAMI = no_hp_suamiupdate.getText().toString();
    }


    //Berisi Statement-Statement Untuk Menyimpan Data Pada Database
    private void saveData() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setTitle("Apakah Anda Yakin Mengubah Data ?")
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
                        // int id =1;
                        create.update(QuizContract.Register.TABLE_NAME, values, SETID + " = ?",
                                new String[] { String.valueOf(id) });
                        // create.update(QuizContract.Register.TABLE_NAME, values, nama_ibu_update + " = ? ",new String[]{ String.valueOf(nama_ibu_update.getText().toString()) });
                        Toast.makeText(UpdateProfile.this, "Profile Telah Di Update", Toast.LENGTH_SHORT).show();
                        Intent Intent = new Intent(UpdateProfile.this, ProfileActivity.class);
                        startActivity(Intent);
                        //finish();
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






























        //Mendapatkan Repository dengan Mode Menulis

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MM - yyyy", Locale.getDefault());
        tanggal_lahirupdate.setText(dateFormat.format(calendar.getTime()));
        tanggal_lahirupdate.setFocusable(false);

        //Set Tanggal Lahir
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DATE, dayOfMonth);
        thatDay.set(Calendar.MONTH, month); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, year);

        //Menghitung Umur
        Calendar today = Calendar.getInstance();
        long diff = (today.get(Calendar.YEAR)) - (thatDay.get(Calendar.YEAR));
        String yearString = Long.toString(diff);
        umurupdate.setText(yearString + " Tahun");
    }

    @Override
    public void onBackPressed() {
        Intent Intent = new Intent(UpdateProfile.this, ProfileActivity.class);
        Toast.makeText(this, "Update Profile Telah Dibatalkan", Toast.LENGTH_SHORT).show();
        startActivity(Intent);
    }
}
