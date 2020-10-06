package id.untad.projectdeteksidiniresikokehamilan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.untad.projectdeteksidiniresikokehamilan.Model.Register;

public class RegisterHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    String status = "a";
    boolean cek = false;
    private SQLiteDatabase db;
    private boolean sett = true;

    public RegisterHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                QuizContract.Register.TABLE_NAME + " ( " +
                QuizContract.Register._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.Register.NAMA_IBU + " TEXT, " +
                QuizContract.Register.TANGGAL_LAHIR + " TEXT, " +
                QuizContract.Register.UMUR + " TEXT, " +
                QuizContract.Register.AGAMA + " TEXT, " +
                QuizContract.Register.PEKERJAAN_IBU + " TEXT, " +
                QuizContract.Register.PENDIDIKAN_IBU + " TEXT, " +
                QuizContract.Register.GOLONGAN_DARAH + " TEXT, " +
                QuizContract.Register.ALAMAT + " TEXT, " +
                QuizContract.Register.NAMA_SUAMI + " TEXT, " +
                QuizContract.Register.PEKERJAAN_SUAMI + " TEXT, " +
                QuizContract.Register.PENDIDIKAN_SUAMI + " TEXT, " +
                QuizContract.Register.NO_HP_IBU + " TEXT, " +
                QuizContract.Register.NO_HP_SUAMI + " TEXT, " +
                QuizContract.Register.EMAIL + " TEXT" +
                ")";
        db.execSQL(SQL_CREATE_QUESTION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.Register.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }

    private boolean sett() {
        return true;
    }


    private void addQuestion(Register register) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.Register.NAMA_IBU, register.getNama_ibu());
        cv.put(QuizContract.Register.TANGGAL_LAHIR, register.getTanggal_lahir());
        cv.put(QuizContract.Register.UMUR, register.getUmur());
        cv.put(QuizContract.Register.AGAMA, register.getAgama());
        cv.put(QuizContract.Register.PEKERJAAN_IBU, register.getPekerjaan_ibu());
        cv.put(QuizContract.Register.PENDIDIKAN_IBU, register.getPendidikan_ibu());
        cv.put(QuizContract.Register.GOLONGAN_DARAH, register.getGolongan_darah());
        cv.put(QuizContract.Register.ALAMAT, register.getAlamat());
        cv.put(QuizContract.Register.NAMA_SUAMI, register.getNama_suami());
        cv.put(QuizContract.Register.PEKERJAAN_SUAMI, register.getPekerjaan_suami());
        cv.put(QuizContract.Register.PENDIDIKAN_SUAMI, register.getPendidikan_suami());
        cv.put(QuizContract.Register.NO_HP_IBU, register.getNo_hp_ibu());
        cv.put(QuizContract.Register.NO_HP_SUAMI, register.getNo_hp_suami());
        cv.put(QuizContract.Register.EMAIL, register.getEmail());
        db.insert(QuizContract.Register.TABLE_NAME, null, cv);
    }

    public boolean check() {
        return cek ;
    }

}