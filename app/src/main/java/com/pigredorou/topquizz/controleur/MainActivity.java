package com.pigredorou.topquizz.controleur;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.topquizz.R;
import com.pigredorou.topquizz.modele.Utilisateur;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mGreetingText;
    private Button mQuizzButton;
    private Button mComptesButton;
    private Button mTodoButton;
    private Utilisateur mUtilisateur;
    public static final int GAME_ACTIVITY_REQUEST_CODE=14;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_PRENOM = "PREF_KEY_PRENOM";

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::onCreate()");

        mUtilisateur = new Utilisateur();
        mPreferences = getPreferences(MODE_PRIVATE);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mQuizzButton = (Button) findViewById(R.id.activity_main_quizz_button);
        mComptesButton = (Button) findViewById(R.id.activity_main_comptes_button);
        mTodoButton = (Button) findViewById(R.id.activity_main_todo_button);

        mQuizzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent quizzActivity = new Intent(MainActivity.this, QuizzActivity.class);
                startActivityForResult(quizzActivity, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

        // Use the activity listener for all this buttons.
        mComptesButton.setOnClickListener(this);
        mTodoButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "PROCHAINEMENT", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }
}
