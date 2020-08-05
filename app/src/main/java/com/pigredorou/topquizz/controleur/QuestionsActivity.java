package com.pigredorou.topquizz.controleur;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.topquizz.R;
import com.pigredorou.topquizz.modele.BanqueQuestion;
import com.pigredorou.topquizz.modele.Question;

import java.util.Arrays;

import static java.lang.System.out;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextScore;
    private TextView mTextQuestion;
    private Button mButtonReponse1;
    private Button mButtonReponse2;
    private Button mButtonReponse3;
    private Button mButtonReponse4;

    private BanqueQuestion mBanqueQuestion;
    private Question mQuestionEnCours;
    private int mScore;
    private int mNombreDeQuestionsRestantes;
    private int mNombreDeQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "ScoreCourant";
    public static final String BUNDLE_STATE_QUESTION = "QuestionCourante";
    public static final String BUNDLE_STATE_QUESTION_TOTAL = "QuestionTotale";

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        out.println("GameActivity::onCreate()");

        final Intent intent = getIntent();
        mNombreDeQuestions = intent.getIntExtra(QuizzActivity.PREF_KEY_NOMBRE_QUESTION, 0);
        mNombreDeQuestionsRestantes = mNombreDeQuestions;
        //Toast.makeText(this, "intent " + mNombreDeQuestions, Toast.LENGTH_SHORT).show();

        if (savedInstanceState != null)
        {
            if (mNombreDeQuestions == 0)
                mNombreDeQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION_TOTAL);
            //Toast.makeText(this, "saved " + mNombreDeQuestions, Toast.LENGTH_SHORT).show();
            mNombreDeQuestionsRestantes = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
        }

        mEnableTouchEvents = true;

        mTextScore = (TextView) findViewById(R.id.activity_score_text);
        mTextQuestion = (TextView) findViewById(R.id.activity_game_question_text);
        mButtonReponse1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mButtonReponse2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mButtonReponse3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mButtonReponse4 = (Button) findViewById(R.id.activity_game_answer4_btn);

        mBanqueQuestion = this.generateQuestions();

        // Use the same listener for the four buttons.
        mButtonReponse1.setOnClickListener(this);
        mButtonReponse2.setOnClickListener(this);
        mButtonReponse3.setOnClickListener(this);
        mButtonReponse4.setOnClickListener(this);

        // Use the tag property to 'name' the buttons
        mButtonReponse1.setTag(0);
        mButtonReponse2.setTag(1);
        mButtonReponse3.setTag(2);
        mButtonReponse4.setTag(3);

        mQuestionEnCours = mBanqueQuestion.getQuestion();
        this.afficheQuestion(mQuestionEnCours);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_QUESTION_TOTAL, mNombreDeQuestions);
        outState.putInt(BUNDLE_STATE_QUESTION, mNombreDeQuestionsRestantes);
        outState.putInt(BUNDLE_STATE_SCORE, mScore);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mQuestionEnCours.getIndexReponse()) {
            // Good answer
            Toast.makeText(this, "Bonne réponse", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // Wrong answer
            Toast.makeText(this, "Mauvaise réponse !", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                if (--mNombreDeQuestionsRestantes <= 0) {
                    FinDePartie();
                }
                else {
                    mQuestionEnCours = mBanqueQuestion.getQuestion();
                    afficheQuestion(mQuestionEnCours);
                }
            }
        }, 1000);

    }

    private void majScore() {
        String textQuestionRestante = "";
        if (mNombreDeQuestionsRestantes == 1)
            textQuestionRestante = "dernière question";
        else if (mNombreDeQuestionsRestantes == 2)
            textQuestionRestante = (mNombreDeQuestionsRestantes-1) + " question restante";
        else
            textQuestionRestante = (mNombreDeQuestionsRestantes-1) + " questions restantes";
        mTextScore.setText("Score : " + mScore + " - " + textQuestionRestante);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void FinDePartie() {
        String mTitre;
        int nombreMauvaiseReponse;
        // No question left, end the game
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        nombreMauvaiseReponse = mNombreDeQuestions - mScore;
        if (nombreMauvaiseReponse == 0)
            mTitre = "PARFAIT !";
        else if (nombreMauvaiseReponse == 1)
            mTitre = "Bien joué !";
        else
            mTitre = "Terminé";

        // Oblige a appuyer sur OK
        builder.setCancelable(false);
        // Affichage du score final
        builder.setTitle(mTitre)
                .setMessage("Votre score : " + mScore + "/" + mNombreDeQuestions)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Bouton OK, prend la couleur "colorAccent"
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void afficheQuestion(final Question question) {
        // Affiche la question et affecte la réponse aux 4 boutons
        majScore();
        mTextQuestion.setText(question.getQuestion());
        mButtonReponse1.setText(question.getListeChoix().get(0));
        mButtonReponse2.setText(question.getListeChoix().get(1));
        mButtonReponse3.setText(question.getListeChoix().get(2));
        mButtonReponse4.setText(question.getListeChoix().get(3));
    }

    private BanqueQuestion generateQuestions() {
        Question question1 = new Question("Combien de côtés arrondis comporte un polygone ?",
                Arrays.asList("6 côtés", "2 côtés", "4 côtés", "aucun"),3);

        Question question2 = new Question("Combien de côtés comporte un quadrilatère ?",
                Arrays.asList("6 côtés", "2 côtés", "4 côtés", "8 côtés"),2);

        Question question3 = new Question("Lequel de ces polygones comporte 3 côtés ?",
                Arrays.asList("Un cercle", "Un carré", "Un triangle", "Un rectangle"),2);

        Question question4 = new Question("Combien de faces un cube possède-t-il ?",
                Arrays.asList("6", "5", "7", "4"),0);

        Question question5 = new Question("Lequel de ces polygones comporte 4 côtés égaux ?",
                Arrays.asList("Un carré", "Un triangle", "Un rectangle", "Un cercle"),0);

        Question question6 = new Question("Lequel de ces solides comporte 8 sommets, 12, arrêtes et 6 faces qui sont des carrés ou des rectangles ?",
                Arrays.asList("Une pyramide", "Un pavé droit", "Un carré", "Une boule"),1);

        Question question7 = new Question("Combien de pièces de 20 centimes d'euro me faut-il pour avoir 1€ ?",
                Arrays.asList("4 pièces", "5 pièces", "7 pièces", "10 pièces"),1);

        Question question8 = new Question("Combien faut-il de billet de 5€ pour avoir 75€ ?",
                Arrays.asList("10 billets", "15 billets", "5 billets", "20 billets"),1);

        Question question9 = new Question("Combien dois-tu donner de pièces de 10 centimes d'euro pour payer 1€ ?",
                Arrays.asList("42 pièces", "15 pièces", "10 pièces", "25 pièces"),2);

        return new BanqueQuestion(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9));
    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("GameActivity::onDestroy()");
    }
}
