package com.pigredorou.topquizz.controleur;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.topquizz.R;
import com.pigredorou.topquizz.modele.Utilisateur;

import static java.lang.System.out;

public class QuizzActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private TextView mNombreQuestion;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mQuitButton;
    private Utilisateur mUtilisateur;
    private NumberPicker mNumberPickerTest;
    public static final int GAME_ACTIVITY_REQUEST_CODE=14;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_PRENOM = "PREF_KEY_PRENOM";
    public static final String PREF_KEY_NOMBRE_QUESTION = "PREF_KEY_NOMBRE_QUESTION";
    public static final int mNombreMinQuestion = 4;
    public static final int mNombreMaxQuestion = 9;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            int score = data.getIntExtra(QuestionsActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();

            if (score == 4)
                greetUser("PARFAIT ");
            else if (score > 2)
                greetUser("Bien joué, ");
            else
                greetUser("Terminé, ");
        }
    }

    private void greetUser(String texteDeBienvenue) {
        String firstname = mPreferences.getString(PREF_KEY_PRENOM, null);

        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = texteDeBienvenue + firstname
                    + "!\nTon dernier score était " + score
                    + ", feras-tu mieux cette fois-ci ?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        System.out.println("QuizzActivity::onCreate()");

        mUtilisateur = new Utilisateur();
        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_quizz_greeting_txt);
        mNombreQuestion = (TextView) findViewById(R.id.activity_quizz_texte_nombre_question);
        mNameInput = (EditText) findViewById(R.id.activity_quizz_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_quizz_play_button);
        mQuitButton = (Button) findViewById(R.id.activity_quizz_quit_button);
        mNumberPickerTest = (NumberPicker) findViewById(R.id.activity_quizz_choix_nombre_question);

        mNumberPickerTest.setTextColor(Color.parseColor("#FFFFFF"));
        mNumberPickerTest.setMinValue(mNombreMinQuestion);
        mNumberPickerTest.setMaxValue(mNombreMaxQuestion);
        mNumberPickerTest.setValue(5);
        mNumberPickerTest.setWrapSelectorWheel(false);

        greetUser("Ravi de te revoir ");

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = mNameInput.getText().toString();
                mUtilisateur.setPrenom(prenom);

                // Sauvegarde des préférences
                mPreferences.edit().putInt(PREF_KEY_NOMBRE_QUESTION, mNumberPickerTest.getValue()).apply();
                mPreferences.edit().putString(PREF_KEY_PRENOM, mUtilisateur.getPrenom()).apply();

                // Utilisateur clique sur le bouton
                Intent gameActivity = new Intent(QuizzActivity.this, QuestionsActivity.class);
                //startActivity(gameActivity);
                gameActivity.putExtra(PREF_KEY_NOMBRE_QUESTION, mNumberPickerTest.getValue());
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);

            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à l'accueil
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        out.println("QuizzActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("QuizzActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("QuizzActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("QuizzActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("QuizzActivity::onDestroy()");
    }
}
