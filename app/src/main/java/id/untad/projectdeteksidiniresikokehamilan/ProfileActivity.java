package id.untad.projectdeteksidiniresikokehamilan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.untad.projectdeteksidiniresikokehamilan.Adapter.RecyclerViewAdapter;
import id.untad.projectdeteksidiniresikokehamilan.Database.DeteksiHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizDbHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.RegisterHelper;
import id.untad.projectdeteksidiniresikokehamilan.Model.Deteksi;
import id.untad.projectdeteksidiniresikokehamilan.Model.Question;
import id.untad.projectdeteksidiniresikokehamilan.Model.Register;
import id.untad.projectdeteksidiniresikokehamilan.R;

import static android.widget.LinearLayout.VERTICAL;
public class ProfileActivity extends AppCompatActivity {

    private QuizDbHelper dbHelper;
    private RegisterHelper reghelp;
    private ImageButton back, update;
    private TextView namaibu, Tanggallahir,umur, agama, pekerjaanibu, pendidikanibu, golongandarah, alamat, namasuami,pekerjaansuami, pendidikansuami, nohpibu, nohpsuami;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        dbHelper = new QuizDbHelper(getBaseContext());
        update = findViewById(R.id.update);
        back = findViewById(R.id.back);
        namaibu = findViewById(R.id.nama);
        Tanggallahir = findViewById(R.id.tanggallahir);
        umur = findViewById(R.id.umur);
        agama = findViewById(R.id.agama);
        pekerjaanibu = findViewById(R.id.pekerjaanIbu);
        pendidikanibu = findViewById(R.id.PendidikanIbu);
        golongandarah = findViewById(R.id.golonganDarah);
        alamat = findViewById(R.id.alamat);
        namasuami = findViewById(R.id.namaSuami);
        pekerjaansuami = findViewById(R.id.pekerjaanSuami);
        pendidikansuami = findViewById(R.id.pendidikanSuami);
        nohpibu = findViewById(R.id.noHpIbu);
        nohpsuami = findViewById(R.id.noHpSuami);
        reghelp = new RegisterHelper(getBaseContext());
        RegisterHelper dbHelper = new RegisterHelper(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(Intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register person = new Register();
                person.setNama_ibu(namaibu.getText().toString());
                person.setTanggal_lahir(Tanggallahir.getText().toString());
                person.setUmur(umur.getText().toString());
                person.setAgama(agama.getText().toString());
                person.setPekerjaan_ibu(pekerjaanibu.getText().toString());
                person.setPendidikan_ibu(pendidikanibu.getText().toString());
                person.setGolongan_darah(golongandarah.getText().toString());
                person.setAlamat(alamat.getText().toString());
                person.setNama_suami(namasuami.getText().toString());
                person.setPekerjaan_suami(pekerjaansuami.getText().toString());
                person.setPendidikan_suami(pendidikansuami.getText().toString());
                person.setNo_hp_ibu(nohpibu.getText().toString());
                person.setNo_hp_suami(nohpsuami.getText().toString());
              //  SQLiteDatabase ReadData = reghelp.getReadableDatabase();
             //   ReadData.execSQL("DROP TABLE IF EXISTS " + QuizContract.Register.TABLE_NAME);
                Intent moveWithObjectIntent = new Intent(ProfileActivity.this, UpdateProfile.class);
                moveWithObjectIntent.putExtra(UpdateProfile.EXTRA_PERSON, person);
                startActivity(moveWithObjectIntent);
            }
        });

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select * from register where _id='"+1+"'",null, null);
//        String s1 =  c.getString(2);
        c.moveToPosition(0);
        String itemname = c.getString(c.getColumnIndex("nama_ibu"));
        namaibu.setText(itemname);
        String tanggallahir= c.getString(c.getColumnIndex("tanggal_lahir"));
        Tanggallahir.setText(tanggallahir);
        String Umur = c.getString(c.getColumnIndex("umur"));
        umur.setText(Umur);
        String Agama = c.getString(c.getColumnIndex("agama"));
        agama.setText(Agama);
        String PekerjaanIbu = c.getString(c.getColumnIndex("pekerjaan_ibu"));
        pekerjaanibu.setText(PekerjaanIbu);
        String PendidikanIbu = c.getString(c.getColumnIndex("pendidikan_ibu"));
        pendidikanibu.setText(PendidikanIbu);
        String GolonganDarah = c.getString(c.getColumnIndex("golongan_darah"));
        golongandarah.setText(GolonganDarah);
        String Alamat = c.getString(c.getColumnIndex("alamat"));
        alamat.setText(Alamat);
        String NamaSuami  = c.getString(c.getColumnIndex("nama_suami"));
        namasuami.setText(NamaSuami);
        String PekerjaanSuami = c.getString(c.getColumnIndex("pekerjaan_suami"));
        pekerjaansuami.setText(PekerjaanSuami);
        String PendidikanSuami = c.getString(c.getColumnIndex("pendidikan_suami"));
        pendidikansuami.setText(PendidikanSuami);
        String NoHpIbu = c.getString(c.getColumnIndex("no_hp_ibu"));
        nohpibu.setText(NoHpIbu);
        String NoHpSuami = c.getString(c.getColumnIndex("no_hp_suami"));
        nohpsuami.setText(NoHpSuami);
        c.close();
        }

    @Override
    public void onBackPressed() {
        Intent Intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(Intent);
    }
    }