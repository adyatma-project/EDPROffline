package id.untad.projectdeteksidiniresikokehamilan.Database;

import android.provider.BaseColumns;

public final class QuizContract {
    private QuizContract() {
    }
    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }

    public static class Register implements BaseColumns {
        public static final String TABLE_NAME = "register";
        public static final String NAMA_IBU = "nama_ibu";
        public static final String TANGGAL_LAHIR = "tanggal_lahir";
        public static final String UMUR = "umur";
        public static final String AGAMA = "agama";
        public static final String PEKERJAAN_IBU = "pekerjaan_ibu";
        public static final String PENDIDIKAN_IBU = "pendidikan_ibu";
        public static final String GOLONGAN_DARAH = "golongan_darah";
        public static final String ALAMAT = "alamat";
        public static final String NAMA_SUAMI = "nama_suami";
        public static final String PEKERJAAN_SUAMI = "pekerjaan_suami";
        public static final String PENDIDIKAN_SUAMI = "pendidikan_suami";
        public static final String NO_HP_IBU = "no_hp_ibu";
        public static final String NO_HP_SUAMI = "no_hp_suami";
        public static final String EMAIL = "email";
    }

    public static class Deteksi implements BaseColumns {
        public static final String TABLE_NAME = "deteksi";
        public static final String PERTANYAAN = "pertanyaan";
        public static final String JAWABAN = "jawaban";

    }


}
