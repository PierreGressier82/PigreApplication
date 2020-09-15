package com.pigredorou.topquizz.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pigredorou.topquizz.R;

public class TresFute extends AppCompatActivity {

    public static final int TRES_FUTE_SCORE_ACTIVITY_REQUEST_CODE=21;
    public static final int TRES_FUTE_SOLO_ACTIVITY_REQUEST_CODE=22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_fute_main);

        Button mButton_feuilleScore = findViewById(R.id.feuille_score);
        Button mButton_jeuSolo = findViewById(R.id.jeu_solo);

        mButton_feuilleScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent todoActivity = new Intent(TresFute.this, TresFuteScore.class);
                startActivityForResult(todoActivity, TRES_FUTE_SCORE_ACTIVITY_REQUEST_CODE);
            }
        });

        mButton_jeuSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent todoActivity = new Intent(TresFute.this, TresFuteSolo.class);
                startActivityForResult(todoActivity, TRES_FUTE_SOLO_ACTIVITY_REQUEST_CODE);
            }
        });
    }
}
