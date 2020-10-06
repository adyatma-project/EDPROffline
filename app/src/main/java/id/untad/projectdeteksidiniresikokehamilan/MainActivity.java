package id.untad.projectdeteksidiniresikokehamilan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import id.untad.projectdeteksidiniresikokehamilan.Database.DeteksiHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.RegisterHelper;

public class MainActivity extends AppCompatActivity implements DatePickerFragment.DialogDateListener {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    public static final String SHARED_REGISTER = "sharedRegister";
    public static final String KEY_REGISTER = "keyRegister";
    public static final String EXTRA_DETECT_MAIN = "extra_detect_main";
    private static final int REQUEST_CODE_QUIZ = 1;
    private static final int REQUEST_CODE_REGISTER = 2;
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
    private int highscore, register;
    private View viewperkiraan, viewDate;
    private TextView tvperkirrn1, tvjudulprediksi1, tvpesan1, textpesan1, perkiraanminggu1, tanggalperkiraan1;
    private ImageView imgperkiraan1, imgdate1, imgd;
    private ImageButton imgDeteksi1, imgProfile1;
    private RegisterHelper regh;
    private Button Daftar, masukkantanggal1;
    private DeteksiHelper detek;
    // private ConstraintLayout rel1;
    int a =0;
    int b =0;
    int c =0;
    private View pesan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // deteksi = findViewById(R.id.text);
        tvpesan1 = findViewById(R.id.tvpesan);
        viewperkiraan = findViewById(R.id.viewperkiraan);
        viewDate = findViewById(R.id.viewDate);
        textpesan1 = findViewById(R.id.text_pesan);
        detek = new DeteksiHelper(getBaseContext());
        tvpesan1.setVisibility(View.GONE);
        imgDeteksi1 = findViewById(R.id.imgDeteksi);
        imgProfile1 = findViewById(R.id.imgprofile);
        Daftar = findViewById(R.id.imgregistrasi);
        tvjudulprediksi1 = findViewById(R.id.tvjudulprediksi);
        masukkantanggal1 = findViewById(R.id.masukkantanggal);
        tanggalperkiraan1 = findViewById(R.id.tanggal_perkiraan_lahir);
        perkiraanminggu1 = findViewById(R.id.perkiraan_minggu);
        imgProfile1.setEnabled(false);
        tvperkirrn1 = findViewById(R.id.tvperkirrn);
        imgperkiraan1 = findViewById(R.id.imgperkiraan);
        imgdate1 = findViewById(R.id.imgdate);
        imgDeteksi1.setEnabled(false);
        pesan = findViewById(R.id.viewpesan);
        pesan.setVisibility(View.GONE);
        LoadPrediksi();
        loadHighscore();
        loadRegister();
        getDate();



        imgProfile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a();
            }
        });

        imgDeteksi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b();
            }
        });

        Daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c();
            }
        });

        masukkantanggal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG1);
            }
        });

        imgdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG2);
            }
        });

    }
    @Override
    protected void onResume() {
        LoadPrediksi();
        loadRegister();
        loadHighscore();
        super.onResume();
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd - MM - yyyy", Locale.getDefault());

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.DATE, dayOfMonth);
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEE d-MMM-yyyy");
        c1.add(c1.DATE, 280);
        Date future = c1.getTime();
       // String strdate1 = sdf1.format(""future);
        //Set Data Kelahiran
        Calendar jumlah = Calendar.getInstance();
        jumlah.set(Calendar.DATE, dayOfMonth);
        jumlah.set(Calendar.MONTH, month); // 0-11 so 1 less
        jumlah.set(Calendar.YEAR, year);

        //SimpleDateFormat dateFormata = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        //nama_suami.setText(dateFormata.format(jumlah.getTime()));

        SharedPreferences sha1 = getSharedPreferences(SHARED_DATE, MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sha1.edit();
        editor1.putInt(KEYA_DATE, dayOfMonth);
        editor1.apply();

        SharedPreferences sha2 = getSharedPreferences(SHARED_MONTH, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sha2.edit();
        editor2.putInt(KEY_MONTH, month);
        editor2.apply();

        SharedPreferences sha3 = getSharedPreferences(SHARED_YEAR, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sha3.edit();
        editor3.putInt(KEY_YEAR, year);
        editor3.apply();
        LoadPrediksi();
    }

    public void LoadPrediksi(){
        SharedPreferences load1 = getSharedPreferences(SHARED_DATE, MODE_PRIVATE);
        a = load1.getInt(KEYA_DATE, 0);
        SharedPreferences load2 = getSharedPreferences(SHARED_MONTH, MODE_PRIVATE);
        b = load2.getInt(KEY_MONTH, 0);
        SharedPreferences load3 = getSharedPreferences(SHARED_YEAR, MODE_PRIVATE);
        c = load3.getInt(KEY_YEAR, 0);

        if (a >= 1){
            Calendar load = Calendar.getInstance();
            load.set(Calendar.DATE, a);
            load.set(Calendar.MONTH, b); // 0-11 so 1 less
            load.set(Calendar.YEAR, c);


            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.DATE, a);
            c1.set(Calendar.MONTH, b); // 0-11 so 1 less
            c1.set(Calendar.YEAR, c);
            SimpleDateFormat sdf1 = new SimpleDateFormat("EEE d-MMM-yyyy");
            c1.add(Calendar.DATE, 280);
            Date future = c1.getTime();
            String strdate1 = sdf1.format(future);
            tanggalperkiraan1.setText("Diperkirakan Lahir Pada : " + strdate1);



            Calendar today = Calendar.getInstance();
            long diff = (c1.getTimeInMillis()) - (today.getTimeInMillis());
            long week = (diff / (24 * 60 * 60 * 1000 * 7));
            long we = (week - week - week);
            if (week < 0) {
                perkiraanminggu1.setText("Diperkirakan Lahir  " + we + "  Minggu Yang Lalu");
            }
            else {
                perkiraanminggu1.setText("Diperkirakan  " + week + "  Minggu Lagi");
            }

            viewDate.setVisibility(View.VISIBLE);
            perkiraanminggu1.setVisibility(View.VISIBLE);
            tanggalperkiraan1.setVisibility(View.VISIBLE);
            tvjudulprediksi1.setVisibility(View.VISIBLE);
            imgdate1.setVisibility(View.VISIBLE);

            viewperkiraan.setVisibility(View.GONE);
            masukkantanggal1.setVisibility(View.GONE);
            imgperkiraan1.setVisibility(View.GONE);
            tvperkirrn1.setVisibility(View.GONE);
        }
    }


    public void getDate() {

        Calendar c1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEE d-MMM-yyyy");
        c1.add(Calendar.DATE, 280);
        Date future = c1.getTime();
        String strdate1 = sdf1.format(future);

        //Date today = c.getTime();
        //c.add(Calendar.DATE, 90);
        //Date future = c.getTime();
        //sesan.setText("Today: " + today + "\n" + "Future: " + future);

        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DATE, 25);
        thatDay.set(Calendar.MONTH, 4); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, 2020);

        Calendar today = Calendar.getInstance();

        long diff = (today.getTimeInMillis()) - (thatDay.getTimeInMillis());
        long days = (diff / (24 * 60 * 60 * 1000 * 7));

        // long daysDiff = TimeUnit.MILLISECONDS.toDays(days);
        //perkiraanminggu.setText(days + "Minggu Lagi");
    }





    private void a() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void b() {
        Intent intent = new Intent(MainActivity.this, TambahDeteksiActivity.class);
        startActivity(intent);
    }

    private void c() {
        Intent intent = new Intent(MainActivity.this, HalamanRegisterAwal.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == REQUEST_CODE_REGISTER) {
            if (resultCode == RESULT_OK) {
                int register = data.getIntExtra(HalamanRegisterAwal.EXTRA_NAMA, 0);
                updateRegister(register);
            }
        }
    }


    private void loadHighscore() {
        Resources res = getResources();
        Drawable red = ResourcesCompat.getDrawable(res, R.drawable.shape_red, null);
        Drawable green = ResourcesCompat.getDrawable(res, R.drawable.shape_green, null);
        Drawable yellow = ResourcesCompat.getDrawable(res, R.drawable.shape_yellow, null);
        View rel1 = findViewById(R.id.viewpesan);
        SharedPreferences prefsk = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefsk.getInt(KEY_HIGHSCORE, 0);

        if (highscore < 1) {
            tvpesan1.setText("Silahkan Lakukan Deteksi");
        }
        else if (highscore  < 5 ) {
            rel1.setBackground(green);
            tvpesan1.setTextColor(Color.GREEN);
            tvpesan1.setText("Nilai: " + highscore + ", Resiko Kehamilan Anda RENDAH");
        } else if (highscore < 10) {
            rel1.setBackground(yellow);
            tvpesan1.setTextColor(Color.YELLOW);
            tvpesan1.setText("Nilai: " + highscore + ", Resiko Kehamilan Anda TINGGI");
        } else if (highscore >10) {
            rel1.setBackground(red);
            tvpesan1.setTextColor(Color.RED);
            tvpesan1.setText("Nilai: " + highscore + ", Resiko Kehamilan Anda SANGAT TINGGI");
        }
    }


    private void loadRegister() {
        SharedPreferences prefs = getSharedPreferences(SHARED_REGISTER, MODE_PRIVATE);
        register = prefs.getInt(KEY_REGISTER, 0);
        if (register == 400) {
            textpesan1.setVisibility(View.GONE);
            //tvpesan.setVisibility(View.VISIBLE);
            Daftar.setVisibility(View.GONE);
            imgProfile1.setEnabled(true);
            tvpesan1.setVisibility(View.VISIBLE);
            pesan.setVisibility(View.VISIBLE);
            imgDeteksi1.setEnabled(true);
        }
    }


    private void updateRegister(int RegisterNew) {
        register = RegisterNew;
        SharedPreferences prefs = getSharedPreferences(SHARED_REGISTER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_REGISTER, register);
        editor.apply();
    }

}