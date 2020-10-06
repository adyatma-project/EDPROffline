package id.untad.projectdeteksidiniresikokehamilan;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInput;
import java.util.List;

import id.untad.projectdeteksidiniresikokehamilan.Database.DeteksiHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizContract;
import id.untad.projectdeteksidiniresikokehamilan.Database.QuizDbHelper;
import id.untad.projectdeteksidiniresikokehamilan.Database.RegisterHelper;
import id.untad.projectdeteksidiniresikokehamilan.Model.Deteksi;
import id.untad.projectdeteksidiniresikokehamilan.Model.Question;

public class TambahDeteksiActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private String SETPERTANYAAN, SETJAWABAN;
    private static final int REQUEST_CODE_CROSS= 1;
    private TextView textViewQuestion;
    private TextView textViewScore, set;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private ImageView imbak;
    private Button buttonConfirmNext;
    private DeteksiHelper detekhelp;
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int score = 2;
    private String tes = "D";
    private boolean answered, bol;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_deteksi);
        set = findViewById(R.id.set);
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        imbak = findViewById(R.id.imback);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultRb = rb1.getTextColors();
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        detekhelp = new DeteksiHelper(getBaseContext());
        DeteksiHelper detekhelpk = new DeteksiHelper(this);
        bol = detekhelpk.check();
        //Collections.shuffle(questionList);
        showNextQuestion();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked()) {
                        checkAnswer();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });



        imbak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(TambahDeteksiActivity.this, MainActivity.class);
                startActivity(Intent);
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            //buttonConfirmNext.setText("Confirm");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == 1) {
            score = (int) (score + currentQuestion.getAnswerNr());
            textViewScore.setText("Score: " + score);
            tes = "YA";
            showNextQuestion();
            setData();
            saveData();
        } else if (answerNr == 2) {
            //score = (int) -currentQuestion.getAnswerNr();
            //textViewScore.setText("Score: " + score);
            tes = "TIDAK";
            showNextQuestion();
            setData();
            saveData();
        }
    }

    private void finishQuiz() {
        Intent moveWithDataIntent = new Intent(TambahDeteksiActivity.this, CrossCheck.class);
        moveWithDataIntent.putExtra(CrossCheck.EXTRA_DETECT, score );
        startActivity(moveWithDataIntent);
    }


    private void setData() {
        SETPERTANYAAN = textViewQuestion.getText().toString();
        SETJAWABAN = tes;
    }


    private void saveData() {
        SQLiteDatabase create = detekhelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(QuizContract.Deteksi.PERTANYAAN, SETPERTANYAAN);
        values.put(QuizContract.Deteksi.JAWABAN, SETJAWABAN);
        create.insert(QuizContract.Deteksi.TABLE_NAME, null, values);
    }


    protected void DeleteDeteksi() {
        SQLiteDatabase ReadData = detekhelp.getReadableDatabase();
        ReadData.execSQL("DROP TABLE IF EXISTS " + QuizContract.Deteksi.TABLE_NAME);
        Intent Intent = new Intent(TambahDeteksiActivity.this, MainActivity.class);
        startActivity(Intent);
    }

    protected void DeleteQuiz() {
        SQLiteDatabase ReadData = detekhelp.getReadableDatabase();
        ReadData.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            DeleteDeteksi();
           // DeleteQuiz();
          //  finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}