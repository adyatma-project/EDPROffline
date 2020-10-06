package id.untad.projectdeteksidiniresikokehamilan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.untad.projectdeteksidiniresikokehamilan.Adapter.RecyclerViewAdapter;
import id.untad.projectdeteksidiniresikokehamilan.Database.CrossHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.DeteksiHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizDbHelper;
import id.untad.projectdeteksidiniresikokehamilan.Model.Deteksi;
import id.untad.projectdeteksidiniresikokehamilan.Model.Question;

public class CrossCheck extends AppCompatActivity {
    public static final String EXTRA_DETECT = "extra_detect";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    private CrossHelper detek;
    private Button simpan, batal;
    private int get=0;
    private int get1=0;

    private RecyclerViewAdapter adapter;
    private ArrayList<Deteksi> dataList;
    private TextView tvKonfirmasi;
    private List<Question> questionList;
    private int questionCountTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_check);
        dataList = new ArrayList<>();
        detek = new CrossHelper(getBaseContext());
        simpan = findViewById(R.id.buttonsimpan);
        batal = findViewById(R.id.buttonbatal);
        tvKonfirmasi = findViewById(R.id.tvkonfirmasi);
        RecyclerView recyclerView = findViewById(R.id.rv_note);
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        getData();
        //Menggunakan Layout Manager, Dan Membuat List Secara Vertical
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new RecyclerViewAdapter(dataList);
        //Memasang Adapter pada RecyclerView
        recyclerView.setAdapter(adapter);
        //Membuat Underline pada Setiap Item Didalam List
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        recyclerView.addItemDecoration(itemDecoration);

         get = getIntent().getIntExtra(EXTRA_DETECT, 0);
         updateHighscore(get);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hapus();
            }
        });

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Batal();
            }
        });

    }

    private void updateHighscore(int HighscoreNew) {
        get = HighscoreNew;
        get1 = ((get * questionCountTotal)/100);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, get1);
        editor.apply();
    }



    protected void Hapus(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = detek.getReadableDatabase();
        ReadData.execSQL("DROP TABLE IF EXISTS " + QuizContract.Deteksi.TABLE_NAME);
        Toast.makeText(this, "Jawaban Anda Telah Tersimpan", Toast.LENGTH_SHORT).show();



        Intent moveWithDataIntent = new Intent(CrossCheck.this, MainActivity.class);
        moveWithDataIntent.putExtra(MainActivity.EXTRA_DETECT_MAIN, get);
        startActivity(moveWithDataIntent);
        //cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
    }

    protected void Batal(){
        //Mengambil Repository dengan Mode Membaca
        Toast.makeText(this, "Jawaban Anda Telah Dibatalkan", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(CrossCheck.this, MainActivity.class);
        startActivity(intent);
        //cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
    }

    //Berisi Statement-Statement Untuk Mengambi Data dari Database
    @SuppressLint("Recycle")
    protected void getData(){
        //Mengambil Repository dengan Mode Membaca
        SQLiteDatabase ReadData = detek.getReadableDatabase();
        Cursor cursor = ReadData.rawQuery("SELECT * FROM "+ QuizContract.Deteksi.TABLE_NAME,null);

        cursor.moveToFirst();//Memulai Cursor pada Posisi Awal

        //Melooping Sesuai Dengan Jumlan Data (Count) pada cursor
        for(int count=0; count < cursor.getCount(); count++){
            cursor.moveToPosition(count);//Berpindah Posisi dari no index 0 hingga no index terakhir
            //Memasukan semua data dari variable NIM, Nama dan Jurusan ke parameter Class DataFiter
            dataList.add(new Deteksi(cursor.getString(1), cursor.getString(2)));
        }
    }


}