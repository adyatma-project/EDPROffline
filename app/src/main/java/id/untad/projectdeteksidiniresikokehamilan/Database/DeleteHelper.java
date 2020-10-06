package id.untad.projectdeteksidiniresikokehamilan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import id.untad.projectdeteksidiniresikokehamilan.Model.Deteksi;


public class DeleteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 4;
    String status = "a";
    boolean cek = false;
    private SQLiteDatabase db;
    private boolean sett = true;

    public DeleteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "DROP TABLE IF EXISTS " + QuizContract.Deteksi.TABLE_NAME;
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.Deteksi.TABLE_NAME);
        onCreate(db);
    }

    private boolean sett() {
        return true;
    }


    private void addQuestion(Deteksi deteksi) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.Deteksi.PERTANYAAN, deteksi.getPertanyaan());
        cv.put(QuizContract.Deteksi.JAWABAN, deteksi.getJawaban());
        db.insert(QuizContract.Deteksi.TABLE_NAME, null, cv);
    }

    public boolean check() {
        return cek ;
    }

}
