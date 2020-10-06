package id.untad.projectdeteksidiniresikokehamilan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizDbHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.RegisterHelper;

public class SplashScreen extends AppCompatActivity {
    private TextView tvHakCipta;
    private static final String TABLE_LABELS = "register";
    private static final String TABLE_DATABASE = "register";
    private ProgressBar progressBarSplash;
    private ImageView imgLogo;
    private int waktu_loading = 3500;
    private SQLiteOpenHelper openHelper;
    private String table = "register";
    private SQLiteDatabase mDatabase;
    private boolean bol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tvHakCipta = findViewById(R.id.tvhak);
        progressBarSplash = findViewById(R.id.progressBar);
        imgLogo = findViewById(R.id.logo);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splashtransition);
        tvHakCipta.startAnimation(myanim);
        imgLogo.startAnimation(myanim);
       // RegisterHelper dbHelper = new RegisterHelper(this);
      //  bol = dbHelper.check();
        //dbHelper.getAllQuestions();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                Intent home = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(home);
                progressBarSplash.setVisibility(View.GONE);
                finish();
            }
        },waktu_loading);

        /*
        if (bol = true) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent home = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(home);
                    progressBarSplash.setVisibility(View.GONE);
                    finish();
                }
            },waktu_loading);
        }else if (bol = false){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent home = new Intent(SplashScreen.this, HalamanRegisterAwal.class);
                    startActivity(home);
                    progressBarSplash.setVisibility(View.GONE);
                    finish();
                }
            },waktu_loading);
        }
        */

    }
}