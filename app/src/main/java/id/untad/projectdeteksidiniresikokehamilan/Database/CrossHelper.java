package id.untad.projectdeteksidiniresikokehamilan.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.untad.projectdeteksidiniresikokehamilan.Model.Question;

public class CrossHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 4;
    private SQLiteDatabase db;
    public CrossHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.setVersion(oldVersion);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Apakah Anda Hamil Terlalu Muda ( <=16 tahun ) ?", "YA", "TIDAK", 4);
        addQuestion(q1);
        Question q2 = new Question("Apakah Anda Terlalu lambat hamil I, kawin >= 4 th ", "YA", "TIDAK",  4);
        addQuestion(q2);
        Question q3 = new Question("Terlalu tua, hamil I >= 35 th", "YA", "TIDAK",  4);
        addQuestion(q3);
        Question q4 = new Question("Terlalu cepat hamil lagi (<= 2 th)", "YA", "TIDAK",  4);
        addQuestion(q4);
        Question q5 = new Question("Apakah Anda Terlalu lama hamil lagi (>= 10 th)", "YA", "TIDAK",  4);
        addQuestion(q5);
        Question q6 = new Question("Apakah Anda Memiliki Anak Lebih Dari 4 ?", "YA", "TIDAK",  4);
        addQuestion(q6);
        Question q7 = new Question("Apakah Umur Anda  Lebih Dari 35 Tahun ?", "YA", "TIDAK",  4);
        addQuestion(q7);
        Question q8 = new Question("Apakah Tinggi Badan Anda Kurang dari 145cm ?", "YA", "TIDAK",  4);
        addQuestion(q8);
        Question q9 = new Question("Apakah Anda Pernah Mengalami Kegagalan Kehamilan ?", "YA", "TIDAK",  4);
        addQuestion(q9);
        Question q10 = new Question("Apakah Anda Pernah Melahirkan Dengan Tarikan Tang/Vakum ?", "YA", "TIDAK",  4);
        addQuestion(q10);
        Question q11 = new Question("Apakah Anda pernah melahirkan dengan Uri Dirogoh ?", "YA", "TIDAK",  4);
        addQuestion(q11);
        Question q12 = new Question("Apakah Anda Pernah Melahirkan dengan Diberi Infus/Transfusi Darah ?", "YA", "TIDAK",  4);
        addQuestion(q12);
        Question q13 = new Question("Apakah Anda Pernah Melahirkan Melalui Operasi Sesar ?", "YA", "TIDAK",  8);
        addQuestion(q13);
        Question q14 = new Question("Apakah Anda Punya Penyakit Kurang Darah ?", "YA", "TIDAK",  4);
        addQuestion(q14);
        Question q15 = new Question("Apakah Anda Punya Penyakit TBC Paru ?", "YA", "TIDAK",  4);
        addQuestion(q15);
        Question q16 = new Question("Apakah Anda Punya Penyakit Jantung Lemah ?", "YA", "TIDAK",  4);
        addQuestion(q16);
        Question q17 = new Question("Apakah Anda Punya Penyakit Kencing Manis / Diabetes ?", "YA", "TIDAK",  4);
        addQuestion(q17);
        Question q18 = new Question("Apakah Anda Punya Penyakit Menular Seksual ?", "YA", "TIDAK",  4);
        addQuestion(q18);
        Question q19 = new Question("Apakah Anda Bengkak pada Muka/Tungkai dan Tekanan Darah Tinggi ?", "YA", "TIDAK",  4);
        addQuestion(q19);
        Question q20 = new Question("Apakah Anda Hamil Kembar 2 Atau Lebih ?", "YA", "TIDAK",  4);
        addQuestion(q20);
        Question q21 = new Question("Apakah Anda Hamil Kembar Air (Hydraminon) ?", "YA", "TIDAK",  4);
        addQuestion(q21);
        Question q22 = new Question("Apakah Anda Pernah Mengalami Bayi Mati dalam Kandungan ?", "YA", "TIDAK",  4);
        addQuestion(q22);
        Question q23 = new Question("Apakah Kehamilan Anda Kelebihan Bulan ?", "YA", "TIDAK",  4);
        addQuestion(q23);
        Question q24 = new Question("Apakah Letak Bayi Anda Sungsang ?", "YA", "TIDAK",  8);
        addQuestion(q24);
        Question q25 = new Question("Apakah Letak Bayi Anda Lintang ?", "YA", "TIDAK",  8);
        addQuestion(q25);
        Question q26 = new Question("Apakah Anda Pernah Mengalami Pendarahan Pada Kehamilan Ini ?", "YA", "TIDAK",  8);
        addQuestion(q26);
        Question q27 = new Question("Apakah Anda Pre-eklampsia berat/kejang-kejang ?", "YA", "TIDAK",  8);
        addQuestion(q27);
    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }


    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
