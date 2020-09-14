package com.pigredorou.topquizz.controleur;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.topquizz.R;

public class JeuxDeSocieteActivity extends AppCompatActivity {
    private ImageView mImage_Jeu;
    private ImageView mImage_NombreJoueurs;
    private ImageView mImage_AgeMini;
    private ImageView mImage_Duree;
    private ImageView mImage_ReglePDF;
    private ImageView mImage_VideoRegles;
    private TextView mText_TitreJeu;
    private TextView mText_NombreJoueurs;
    private TextView mText_AgeMini;
    private TextView mText_Duree;
    private TextView mText_ReglePDF;
    private TextView mText_RegleSimplifieesTitre;
    private TextView mText_RegleSimplifieesDetail;
    private RelativeLayout mRelativeLayoutImageTitre;
    private RelativeLayout mRelativeLayoutVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("GameActivity::onCreate()");

        setContentView(R.layout.activity_jeux_de_societe);
        getSupportActionBar().hide();

        mImage_Jeu = (ImageView) findViewById(R.id.image_description_jeu);
        mImage_NombreJoueurs = (ImageView) findViewById(R.id.image_nombre_joueurs);
        mImage_AgeMini = (ImageView) findViewById(R.id.image_age_joueur);
        mImage_Duree = (ImageView) findViewById(R.id.image_duree);
        mImage_ReglePDF = (ImageView) findViewById(R.id.image_regle_PDF);
        mText_TitreJeu = (TextView) findViewById(R.id.text_titre_du_jeu);
        mText_NombreJoueurs = (TextView) findViewById(R.id.text_nombre_joueur);
        mText_AgeMini = (TextView) findViewById(R.id.text_age_joueur);
        mText_Duree = (TextView) findViewById(R.id.text_duree);
        mText_ReglePDF = (TextView) findViewById(R.id.text_regles_pdf);
        mImage_VideoRegles = (ImageView) findViewById(R.id.video_regle_ludovox);
        mText_RegleSimplifieesTitre = (TextView) findViewById(R.id.text_titre_regles_simplifiees);
        mText_RegleSimplifieesDetail = (TextView) findViewById(R.id.text_detail_regles_simplifiees);
        mRelativeLayoutImageTitre = (RelativeLayout) findViewById(R.id.relative_layout_image_titre);
        mRelativeLayoutVideo = (RelativeLayout) findViewById(R.id.relative_layout_video);

        mImage_Jeu.setImageResource(R.drawable.jeux_icone_jeu);
        mImage_NombreJoueurs.setImageResource(R.drawable.jeux_icone_nb_pers);
        mImage_AgeMini.setImageResource(R.drawable.jeux_icone_age);
        mImage_Duree.setImageResource(R.drawable.jeux_icone_duree);
        mImage_ReglePDF.setImageResource(R.drawable.jeux_icone_pdf);
        mImage_VideoRegles.setImageResource(R.drawable.jeux_ludovox);

        // Lancement de la vidéo
        mImage_VideoRegles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Affichage de la vidéo
                //    mWeb_VideoRegles.loadUrl("https://www.youtube.com/watch?v=tvI8eFfm6Bk&feature=youtu.be&ab_channel=Ludovox");
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=tvI8eFfm6Bk&feature=youtu.be&ab_channel=Ludovox"));
                try {
                    JeuxDeSocieteActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }

        });

        // Quitter si clique sur le titre du jeu
        mText_TitreJeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retour à l'accueil
                finish();
            }
        });

        // Lien vers le PDF des règles de jeu
        mImage_ReglePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Lien vers le PDF des règles de jeu
        mText_ReglePDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("GameActivity::onResume()");
    }

}
