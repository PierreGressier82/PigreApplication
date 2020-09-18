package com.pigredorou.topquizz.controleur;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.topquizz.R;
import com.pigredorou.topquizz.modele.ImageViewTresFute;
import com.pigredorou.topquizz.modele.TresFuteTools;

import java.util.Objects;

import static com.pigredorou.topquizz.modele.ImageViewTresFute.BLANC;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.JAUNE;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.BLEU;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.ORANGE;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.VERT;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.VIOLET;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.tableauVert;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.tableauOrange;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.tableauViolet;

public class TresFuteSolo extends AppCompatActivity implements View.OnClickListener
{
    //public static int[][] tableauJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    //public static int[][] tableauBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    //public static int[] tableauVert = {-1,1,2,3,4,5,1,2,3,4,5,6};
    //public static int[] tableauOrange = {-1,0,0,0,0,0,0,0,0,0,0,0};
    //public static int[] tableauViolet = {-1,0,0,0,0,0,0,0,0,0,0,0};

    private int[][] tableauClickJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    private static int[] tableauScoreColonneJaune = {10,14,16,20};
    private int[][] tableauClickBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    private static int[] tableauScoreColonneBleu = {0,1,2,4,7,11,16,22,29,37,46,56};
    private int[] tableauClickVert = tableauVert.clone();
    private static int[] tableauScoreColonneVert = {0,1,3,6,10,15,21,28,36,45,55,66};
    private int[] tableauClickOrange = tableauOrange.clone();
    private int[] tableauClickViolet = tableauViolet.clone();

    private ImageViewTresFute[] CasesBleues = new ImageViewTresFute[12];
    private ImageViewTresFute[] CasesVertes = new ImageViewTresFute[12];
    private ImageViewTresFute[] CasesOranges = new ImageViewTresFute[12];
    private ImageViewTresFute[] CasesViolettes = new ImageViewTresFute[12];

    private int[] DeActifs = {1,1,1,1,1,1}; // Tous les dés sont actifs par defaut
    private int[] ValeurDes = {0,0,0,0,0,0};

    // Bonus
    private ImageView imageJauneLigne4Case5;
    private ImageView imageBleuLigne4Case5;

    private TextView mscoreJaune;
    private TextView mscoreBleu;
    private TextView mscoreVert;
    private TextView mscoreOrange;
    private TextView mscoreViolet;
    private TextView mscoreRouge;
    private TextView mscoreTotal;

    private TextView mDeJaune;
    private TextView mDeBleu;
    private TextView mDeVert;
    private TextView mDeOrange;
    private TextView mDeViolet;
    private TextView mDeBlanc;

    private ImageView mLanceDes;
    private ImageView mQuitter;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //System.out.println("TresFute::onCreate()");

        // Masque le bar de titre de l'activité
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Passe l'appli en plein écran et cache la barre de status.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Lancement forcé en mode paysage
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_tres_fute_solo);

        // Chargement des élements du XML
        // TABLEAUX
        mscoreJaune = findViewById(R.id.score_jaune);
        mscoreBleu = findViewById(R.id.score_bleu);
        mscoreVert = findViewById(R.id.score_vert);
        mscoreOrange = findViewById(R.id.score_orange);
        mscoreViolet = findViewById(R.id.score_violet);
        mscoreRouge = findViewById(R.id.score_rouge);
        mscoreTotal = findViewById(R.id.score_total);

        // DES
        mDeJaune = findViewById(R.id.de_jaune);
        mDeBleu = findViewById(R.id.de_bleu);
        mDeVert = findViewById(R.id.de_vert);
        mDeOrange = findViewById(R.id.de_orange);
        mDeViolet = findViewById(R.id.de_violet);
        mDeBlanc = findViewById(R.id.de_blanc);
        mLanceDes = findViewById(R.id.lance_des);
        mQuitter = findViewById(R.id.exit);

        mDeJaune.setOnClickListener(this);
        mDeBleu.setOnClickListener(this);
        mDeVert.setOnClickListener(this);
        mDeOrange.setOnClickListener(this);
        mDeViolet.setOnClickListener(this);
        mDeBlanc.setOnClickListener(this);
        mLanceDes.setOnClickListener(this);
        mQuitter.setOnClickListener(this);

        mDeJaune.setTag("DeJaune");
        mDeBleu.setTag("DeBleu");
        mDeVert.setTag("DeVert");
        mDeOrange.setTag("DeOrange");
        mDeViolet.setTag("DeViolet");
        mDeBlanc.setTag("DeBlanc");
        mLanceDes.setTag("LancerDes");
        mQuitter.setTag("Quitter");

        // Definition des couleurs
        setTableauJaune();
        setTableauBleu();
        setTableauVert();
        setTableauOrange();
        setTableauViolet();
    }

    private void setTableauJaune() {
        // JAUNE
        ImageViewTresFute imageJauneLigne1Case1 = findViewById(R.id.ligne1JauneCase1);
        ImageViewTresFute imageJauneLigne1Case2 = findViewById(R.id.ligne1JauneCase2);
        ImageViewTresFute imageJauneLigne1Case3 = findViewById(R.id.ligne1JauneCase3);
        ImageViewTresFute imageJauneLigne2Case1 = findViewById(R.id.ligne2JauneCase1);
        ImageViewTresFute imageJauneLigne2Case2 = findViewById(R.id.ligne2JauneCase2);
        ImageViewTresFute imageJauneLigne2Case4 = findViewById(R.id.ligne2JauneCase4);
        ImageViewTresFute imageJauneLigne3Case1 = findViewById(R.id.ligne3JauneCase1);
        ImageViewTresFute imageJauneLigne3Case3 = findViewById(R.id.ligne3JauneCase3);
        ImageViewTresFute imageJauneLigne3Case4 = findViewById(R.id.ligne3JauneCase4);
        ImageViewTresFute imageJauneLigne4Case2 = findViewById(R.id.ligne4JauneCase2);
        ImageViewTresFute imageJauneLigne4Case3 = findViewById(R.id.ligne4JauneCase3);
        ImageViewTresFute imageJauneLigne4Case4 = findViewById(R.id.ligne4JauneCase4);

        imageJauneLigne1Case1.setCouleur(JAUNE);
        imageJauneLigne1Case2.setCouleur(JAUNE);
        imageJauneLigne1Case3.setCouleur(JAUNE);
        imageJauneLigne2Case1.setCouleur(JAUNE);
        imageJauneLigne2Case2.setCouleur(JAUNE);
        imageJauneLigne2Case4.setCouleur(JAUNE);
        imageJauneLigne3Case1.setCouleur(JAUNE);
        imageJauneLigne3Case3.setCouleur(JAUNE);
        imageJauneLigne3Case4.setCouleur(JAUNE);
        imageJauneLigne4Case2.setCouleur(JAUNE);
        imageJauneLigne4Case3.setCouleur(JAUNE);
        imageJauneLigne4Case4.setCouleur(JAUNE);

        imageJauneLigne1Case1.setLigne(0);
        imageJauneLigne1Case2.setLigne(0);
        imageJauneLigne1Case3.setLigne(0);
        imageJauneLigne2Case1.setLigne(1);
        imageJauneLigne2Case2.setLigne(1);
        imageJauneLigne2Case4.setLigne(1);
        imageJauneLigne3Case1.setLigne(2);
        imageJauneLigne3Case3.setLigne(2);
        imageJauneLigne3Case4.setLigne(2);
        imageJauneLigne4Case2.setLigne(3);
        imageJauneLigne4Case3.setLigne(3);
        imageJauneLigne4Case4.setLigne(3);

        imageJauneLigne1Case1.setColonne(0);
        imageJauneLigne1Case2.setColonne(1);
        imageJauneLigne1Case3.setColonne(2);
        imageJauneLigne2Case1.setColonne(0);
        imageJauneLigne2Case2.setColonne(1);
        imageJauneLigne2Case4.setColonne(3);
        imageJauneLigne3Case1.setColonne(0);
        imageJauneLigne3Case3.setColonne(2);
        imageJauneLigne3Case4.setColonne(3);
        imageJauneLigne4Case2.setColonne(1);
        imageJauneLigne4Case3.setColonne(2);
        imageJauneLigne4Case4.setColonne(3);

        // Image masqué du Renard
        imageJauneLigne4Case5 = findViewById(R.id.ligne4JauneCase5);
        imageJauneLigne4Case5.setImageResource(R.drawable.tres_fute_renard_rouge);
        imageJauneLigne4Case5.setVisibility(View.INVISIBLE);
    }

    private void setTableauBleu() {
        ImageViewTresFute imageBleuLigne2Case2 = findViewById(R.id.ligne2bleuCase2);
        ImageViewTresFute imageBleuLigne2Case3 = findViewById(R.id.ligne2bleuCase3);
        ImageViewTresFute imageBleuLigne2Case4 = findViewById(R.id.ligne2bleuCase4);
        ImageViewTresFute imageBleuLigne3Case1 = findViewById(R.id.ligne3bleuCase1);
        ImageViewTresFute imageBleuLigne3Case2 = findViewById(R.id.ligne3bleuCase2);
        ImageViewTresFute imageBleuLigne3Case3 = findViewById(R.id.ligne3bleuCase3);
        ImageViewTresFute imageBleuLigne3Case4 = findViewById(R.id.ligne3bleuCase4);
        ImageViewTresFute imageBleuLigne4Case1 = findViewById(R.id.ligne4bleuCase1);
        ImageViewTresFute imageBleuLigne4Case2 = findViewById(R.id.ligne4bleuCase2);
        ImageViewTresFute imageBleuLigne4Case3 = findViewById(R.id.ligne4bleuCase3);
        ImageViewTresFute imageBleuLigne4Case4 = findViewById(R.id.ligne4bleuCase4);

        imageBleuLigne2Case2.setCouleur(BLEU);
        imageBleuLigne2Case3.setCouleur(BLEU);
        imageBleuLigne2Case4.setCouleur(BLEU);
        imageBleuLigne3Case1.setCouleur(BLEU);
        imageBleuLigne3Case2.setCouleur(BLEU);
        imageBleuLigne3Case3.setCouleur(BLEU);
        imageBleuLigne3Case4.setCouleur(BLEU);
        imageBleuLigne4Case1.setCouleur(BLEU);
        imageBleuLigne4Case2.setCouleur(BLEU);
        imageBleuLigne4Case3.setCouleur(BLEU);
        imageBleuLigne4Case4.setCouleur(BLEU);

        imageBleuLigne2Case2.setLigne(1);
        imageBleuLigne2Case3.setLigne(1);
        imageBleuLigne2Case4.setLigne(1);
        imageBleuLigne3Case1.setLigne(2);
        imageBleuLigne3Case2.setLigne(2);
        imageBleuLigne3Case3.setLigne(2);
        imageBleuLigne3Case4.setLigne(2);
        imageBleuLigne4Case1.setLigne(3);
        imageBleuLigne4Case2.setLigne(3);
        imageBleuLigne4Case3.setLigne(3);
        imageBleuLigne4Case4.setLigne(3);

        imageBleuLigne2Case2.setColonne(1);
        imageBleuLigne2Case3.setColonne(2);
        imageBleuLigne2Case4.setColonne(3);
        imageBleuLigne3Case1.setColonne(0);
        imageBleuLigne3Case2.setColonne(1);
        imageBleuLigne3Case3.setColonne(2);
        imageBleuLigne3Case4.setColonne(3);
        imageBleuLigne4Case1.setColonne(0);
        imageBleuLigne4Case2.setColonne(1);
        imageBleuLigne4Case3.setColonne(2);
        imageBleuLigne4Case4.setColonne(3);

        CasesBleues[1]=imageBleuLigne2Case2;
        CasesBleues[2]=imageBleuLigne2Case3;
        CasesBleues[3]=imageBleuLigne2Case4;
        CasesBleues[4]=imageBleuLigne3Case1;
        CasesBleues[5]=imageBleuLigne3Case2;
        CasesBleues[6]=imageBleuLigne3Case3;
        CasesBleues[7]=imageBleuLigne3Case4;
        CasesBleues[8]=imageBleuLigne4Case1;
        CasesBleues[9]=imageBleuLigne4Case2;
        CasesBleues[10]=imageBleuLigne4Case3;
        CasesBleues[11]=imageBleuLigne4Case4;

        imageBleuLigne4Case5 = findViewById(R.id.ligne4bleuCase5);
        imageBleuLigne4Case5.setImageResource(R.drawable.tres_fute_renard_rouge);
        imageBleuLigne4Case5.setVisibility(View.INVISIBLE);
    }

    private void setTableauVert() {
        ImageViewTresFute imageVertLigne1Case2 = findViewById(R.id.ligne1VertCase2);
        ImageViewTresFute imageVertLigne1Case3 = findViewById(R.id.ligne1VertCase3);
        ImageViewTresFute imageVertLigne1Case4 = findViewById(R.id.ligne1VertCase4);
        ImageViewTresFute imageVertLigne1Case5 = findViewById(R.id.ligne1VertCase5);
        ImageViewTresFute imageVertLigne1Case6 = findViewById(R.id.ligne1VertCase6);
        ImageViewTresFute imageVertLigne1Case7 = findViewById(R.id.ligne1VertCase7);
        ImageViewTresFute imageVertLigne1Case8 = findViewById(R.id.ligne1VertCase8);
        ImageViewTresFute imageVertLigne1Case9 = findViewById(R.id.ligne1VertCase9);
        ImageViewTresFute imageVertLigne1Case10 = findViewById(R.id.ligne1VertCase10);
        ImageViewTresFute imageVertLigne1Case11 = findViewById(R.id.ligne1VertCase11);
        ImageViewTresFute imageVertLigne1Case12 = findViewById(R.id.ligne1VertCase12);

       imageVertLigne1Case2.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case3.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case4.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case5.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case6.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case7.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case8.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case9.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case10.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case11.setCouleur(ImageViewTresFute.VERT);
       imageVertLigne1Case12.setCouleur(ImageViewTresFute.VERT);

        imageVertLigne1Case2.setColonne(1);
        imageVertLigne1Case3.setColonne(2);
        imageVertLigne1Case4.setColonne(3);
        imageVertLigne1Case5.setColonne(4);
        imageVertLigne1Case6.setColonne(5);
        imageVertLigne1Case7.setColonne(6);
        imageVertLigne1Case8.setColonne(7);
        imageVertLigne1Case9.setColonne(8);
        imageVertLigne1Case10.setColonne(9);
        imageVertLigne1Case11.setColonne(10);
        imageVertLigne1Case12.setColonne(11);

        CasesVertes[1]=imageVertLigne1Case2;
        CasesVertes[2]=imageVertLigne1Case3;
        CasesVertes[3]=imageVertLigne1Case4;
        CasesVertes[4]=imageVertLigne1Case5;
        CasesVertes[5]=imageVertLigne1Case6;
        CasesVertes[6]=imageVertLigne1Case7;
        CasesVertes[7]=imageVertLigne1Case8;
        CasesVertes[8]=imageVertLigne1Case9;
        CasesVertes[9]=imageVertLigne1Case10;
        CasesVertes[10]=imageVertLigne1Case11;
        CasesVertes[11]=imageVertLigne1Case12;
    }

    private void setTableauOrange() {
        ImageViewTresFute imageOrangeLigne1Case2 = findViewById(R.id.ligne1OrangeCase2);
        ImageViewTresFute imageOrangeLigne1Case3 = findViewById(R.id.ligne1OrangeCase3);
        ImageViewTresFute imageOrangeLigne1Case4 = findViewById(R.id.ligne1OrangeCase4);
        ImageViewTresFute imageOrangeLigne1Case5 = findViewById(R.id.ligne1OrangeCase5);
        ImageViewTresFute imageOrangeLigne1Case6 = findViewById(R.id.ligne1OrangeCase6);
        ImageViewTresFute imageOrangeLigne1Case7 = findViewById(R.id.ligne1OrangeCase7);
        ImageViewTresFute imageOrangeLigne1Case8 = findViewById(R.id.ligne1OrangeCase8);
        ImageViewTresFute imageOrangeLigne1Case9 = findViewById(R.id.ligne1OrangeCase9);
        ImageViewTresFute imageOrangeLigne1Case10 = findViewById(R.id.ligne1OrangeCase10);
        ImageViewTresFute imageOrangeLigne1Case11 = findViewById(R.id.ligne1OrangeCase11);
        ImageViewTresFute imageOrangeLigne1Case12 = findViewById(R.id.ligne1OrangeCase12);

       imageOrangeLigne1Case2.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case3.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case4.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case5.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case6.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case7.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case8.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case9.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case10.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case11.setCouleur(ImageViewTresFute.ORANGE);
       imageOrangeLigne1Case12.setCouleur(ImageViewTresFute.ORANGE);

        imageOrangeLigne1Case2.setColonne(1);
        imageOrangeLigne1Case3.setColonne(2);
        imageOrangeLigne1Case4.setColonne(3);
        imageOrangeLigne1Case5.setColonne(4);
        imageOrangeLigne1Case6.setColonne(5);
        imageOrangeLigne1Case7.setColonne(6);
        imageOrangeLigne1Case8.setColonne(7);
        imageOrangeLigne1Case9.setColonne(8);
        imageOrangeLigne1Case10.setColonne(9);
        imageOrangeLigne1Case11.setColonne(10);
        imageOrangeLigne1Case12.setColonne(11);

        CasesOranges[1]=imageOrangeLigne1Case2;
        CasesOranges[2]=imageOrangeLigne1Case3;
        CasesOranges[3]=imageOrangeLigne1Case4;
        CasesOranges[4]=imageOrangeLigne1Case5;
        CasesOranges[5]=imageOrangeLigne1Case6;
        CasesOranges[6]=imageOrangeLigne1Case7;
        CasesOranges[7]=imageOrangeLigne1Case8;
        CasesOranges[8]=imageOrangeLigne1Case9;
        CasesOranges[9]=imageOrangeLigne1Case10;
        CasesOranges[10]=imageOrangeLigne1Case11;
        CasesOranges[11]=imageOrangeLigne1Case12;
    }

    private void setTableauViolet() {
        ImageViewTresFute imageVioletLigne1Case2 = findViewById(R.id.ligne1VioletCase2);
        ImageViewTresFute imageVioletLigne1Case3 = findViewById(R.id.ligne1VioletCase3);
        ImageViewTresFute imageVioletLigne1Case4 = findViewById(R.id.ligne1VioletCase4);
        ImageViewTresFute imageVioletLigne1Case5 = findViewById(R.id.ligne1VioletCase5);
        ImageViewTresFute imageVioletLigne1Case6 = findViewById(R.id.ligne1VioletCase6);
        ImageViewTresFute imageVioletLigne1Case7 = findViewById(R.id.ligne1VioletCase7);
        ImageViewTresFute imageVioletLigne1Case8 = findViewById(R.id.ligne1VioletCase8);
        ImageViewTresFute imageVioletLigne1Case9 = findViewById(R.id.ligne1VioletCase9);
        ImageViewTresFute imageVioletLigne1Case10 = findViewById(R.id.ligne1VioletCase10);
        ImageViewTresFute imageVioletLigne1Case11 = findViewById(R.id.ligne1VioletCase11);
        ImageViewTresFute imageVioletLigne1Case12 = findViewById(R.id.ligne1VioletCase12);

        imageVioletLigne1Case2.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case3.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case4.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case5.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case6.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case7.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case8.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case9.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case10.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case11.setCouleur(ImageViewTresFute.VIOLET);
        imageVioletLigne1Case12.setCouleur(ImageViewTresFute.VIOLET);

        imageVioletLigne1Case2.setColonne(1);
        imageVioletLigne1Case3.setColonne(2);
        imageVioletLigne1Case4.setColonne(3);
        imageVioletLigne1Case5.setColonne(4);
        imageVioletLigne1Case6.setColonne(5);
        imageVioletLigne1Case7.setColonne(6);
        imageVioletLigne1Case8.setColonne(7);
        imageVioletLigne1Case9.setColonne(8);
        imageVioletLigne1Case10.setColonne(9);
        imageVioletLigne1Case11.setColonne(10);
        imageVioletLigne1Case12.setColonne(11);

        CasesViolettes[1]=imageVioletLigne1Case2;
        CasesViolettes[2]=imageVioletLigne1Case3;
        CasesViolettes[3]=imageVioletLigne1Case4;
        CasesViolettes[4]=imageVioletLigne1Case5;
        CasesViolettes[5]=imageVioletLigne1Case6;
        CasesViolettes[6]=imageVioletLigne1Case7;
        CasesViolettes[7]=imageVioletLigne1Case8;
        CasesViolettes[8]=imageVioletLigne1Case9;
        CasesViolettes[9]=imageVioletLigne1Case10;
        CasesViolettes[10]=imageVioletLigne1Case11;
        CasesViolettes[11]=imageVioletLigne1Case12;
    }

    @Override
   public void onClick(View v) {
        if (v.getClass().toString().endsWith("ImageViewTresFute"))
        {
            int colonne;
            int valeurColonnePrec;
            int valeurColonneSuiv;

            ImageViewTresFute imageClick = findViewById(v.getId());
            colonne = imageClick.getColonne();

            //System.out.println("Colonne "+ colonne);

            switch (imageClick.getCouleur()) {
                case JAUNE:
                    imageClick.onClick(0);
                    tableauClickJaune[imageClick.getLigne()][colonne]=imageClick.getValeur();
                    break;
                case BLEU:
                    imageClick.onClick(0);
                    tableauClickBleu[imageClick.getLigne()][colonne]=imageClick.getValeur();
                    break;
                case VERT:
                    valeurColonnePrec=tableauClickVert[colonne-1];
                    if (colonne < (tableauClickVert.length-1))
                        valeurColonneSuiv=tableauClickVert[colonne+1];
                    else
                        valeurColonneSuiv=0;

                    if (TresFuteTools.isClickPossibleCroix(colonne, valeurColonnePrec, valeurColonneSuiv) == 1) {
                        imageClick.onClick(0);
                        tableauClickVert[colonne] = imageClick.getValeur();
                    }
                    break;
                case ORANGE:
                    valeurColonnePrec=tableauClickOrange[colonne-1];
                    if (colonne < (tableauClickOrange.length-1))
                        valeurColonneSuiv=tableauClickOrange[colonne+1];
                    else
                        valeurColonneSuiv=0;

                    if (TresFuteTools.isClickPossibleChiffres(colonne, valeurColonnePrec, valeurColonneSuiv) == 1) {
                        imageClick.onClick(0);
                        tableauClickOrange[colonne] = imageClick.getValeur();
                    }
                    break;
                case VIOLET:
                    valeurColonnePrec=tableauClickViolet[colonne-1];
                    if (colonne < (tableauClickViolet.length-1))
                        valeurColonneSuiv=tableauClickViolet[colonne+1];
                    else
                        valeurColonneSuiv=0;

                    if (TresFuteTools.isClickPossibleChiffres(colonne, valeurColonnePrec, valeurColonneSuiv) == 1) {
                        int modulo6=1;
                        if (tableauClickViolet[colonne] == 0 && tableauClickViolet[colonne-1] != 6) // Ajout uniquement si case vide
                            modulo6 = (valeurColonnePrec+1)%7;
                        //System.out.println("MODULO "+modulo6);
                        imageClick.onClick(modulo6);
                        tableauClickViolet[imageClick.getColonne()] = imageClick.getValeur();
                    }
                    break;
            }
        }

        System.out.println("");
        System.out.println(v.getClass().toString());
        System.out.println(v.getTag());


        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("DeBlanc"))
        {
            Toast.makeText(this, "En cours de dev", Toast.LENGTH_SHORT).show();
            //utilise_de_bleu(ValeurDes[BLEU]+ValeurDes[BLANC]);
            //desactive_de(BLANC);
        }
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("DeJaune"))
        {
            Toast.makeText(this, "En cours de dev", Toast.LENGTH_SHORT).show();
            //utilise_de_bleu(ValeurDes[BLEU]+ValeurDes[BLANC]);
            //desactive_de(JAUNE);
        }
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("DeBleu"))
        {
            utilise_de_bleu(ValeurDes[BLEU]+ValeurDes[BLANC]);
            //desactive_de(BLEU);
        }
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("DeVert"))
        {
            utilise_de_vert(ValeurDes[VERT]);
            //desactive_de(VERT);
        }
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("DeOrange"))
        {
            utilise_de_orange(ValeurDes[ORANGE]);
            //desactive_de(ORANGE);
        }
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("DeViolet"))
        {
            utilise_de_violet(ValeurDes[VIOLET]);
            //desactive_de(VIOLET);
        }

        // Bouton pour lancer les dés
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("LancerDes"))
            lance_des();
        // Bouton pour quitter
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("Quitter"))
            finish();
        calcul_score_total();
    }

    private int calcul_score_rouge(int scoreMin)
    {
        int score=0;

        if (tableauClickJaune[3][1]==0 && tableauClickJaune[3][2]==0 && tableauClickJaune[3][3]==0) {
            score++;
            imageJauneLigne4Case5.setVisibility(View.VISIBLE);
        }
        else
            imageJauneLigne4Case5.setVisibility(View.INVISIBLE);

        if (tableauClickBleu[3][0]==0 && tableauClickBleu[3][1]==0 && tableauClickBleu[3][2]==0 && tableauClickBleu[3][3]==0) {
            score++;
            imageBleuLigne4Case5.setVisibility(View.VISIBLE);
        }
        else
            imageBleuLigne4Case5.setVisibility(View.INVISIBLE);

        if (tableauClickVert[7]==0)
            score++;
        if (tableauClickOrange[8]!=0)
            score++;
        if (tableauClickViolet[7]!=0)
            score++;

        score*=scoreMin;
        mscoreRouge.setText(String.valueOf(score));
        return score;
    }

    private void calcul_score_total()
    {
        int score;
        int scoreJaune;
        int scoreBleu;
        int scoreVert;
        int scoreOrange;
        int scoreViolet;
        int scoreMin;
        String texteScore;

        // Jaune
        scoreJaune= TresFuteTools.calcul_score_jaune(tableauClickJaune);
        mscoreJaune.setText(String.valueOf(scoreJaune));

        scoreMin=scoreJaune;

        // Bleu
        scoreBleu=TresFuteTools.calcul_score_bleu(tableauClickBleu);
        mscoreBleu.setText(String.valueOf(scoreBleu));
        if (scoreBleu<scoreMin)
            scoreMin=scoreBleu;

        // Vert
        scoreVert=TresFuteTools.calcul_score_vert(tableauClickVert);
        mscoreVert.setText(String.valueOf(scoreVert));
        if (scoreVert<scoreMin)
            scoreMin=scoreVert;

        // Orange
        scoreOrange=TresFuteTools.calcul_score_orange(tableauClickOrange);
        mscoreOrange.setText(String.valueOf(scoreOrange));
        if (scoreOrange<scoreMin)
            scoreMin=scoreOrange;

        // Violet
        scoreViolet=TresFuteTools.calcul_score_violet(tableauClickViolet);
        mscoreViolet.setText(String.valueOf(scoreViolet));

        if (scoreViolet<scoreMin)
            scoreMin=scoreViolet;

        score = scoreJaune + scoreBleu + scoreVert + scoreOrange + scoreViolet + (calcul_score_rouge(scoreMin));

        texteScore = getString(R.string.Score) + " " + score;
        mscoreTotal.setText(texteScore);
    }

    private void lance_des()
    {
        System.out.println("DEBUT LANCE_DES");

        ValeurDes[BLANC]=lance_de();
        ValeurDes[JAUNE]=lance_de();
        ValeurDes[BLEU]=lance_de();
        ValeurDes[VERT]=lance_de();
        ValeurDes[ORANGE]=lance_de();
        ValeurDes[VIOLET]=lance_de();

        mDeBlanc.setText(String.valueOf(ValeurDes[BLANC]));
        mDeJaune.setText(String.valueOf(ValeurDes[JAUNE]));
        mDeBleu.setText(String.valueOf(ValeurDes[BLEU]));
        mDeVert.setText(String.valueOf(ValeurDes[VERT]));
        mDeOrange.setText(String.valueOf(ValeurDes[ORANGE]));
        mDeViolet.setText(String.valueOf(ValeurDes[VIOLET]));

        System.out.println("FIN LANCE_DES");
    }

    private int lance_de()
    {
        int nbAleatoire;
        // génération d'un entier >= 1 et <= 6
        nbAleatoire = (int)(Math.random() * 6 ) +1;

        System.out.println("Resulat DE : "+ nbAleatoire);

        return nbAleatoire;
    }

    private void desactive_de(int couleur)
    {
        DeActifs[couleur]=0;

        switch (couleur)
        {
            case JAUNE:
                mDeJaune.setVisibility(View.INVISIBLE);
                break;
            case BLEU:
                mDeBleu.setVisibility(View.INVISIBLE);
                break;
            case VERT:
                mDeVert.setVisibility(View.INVISIBLE);
                break;
            case ORANGE:
                mDeOrange.setVisibility(View.INVISIBLE);
                break;
            case VIOLET:
                mDeViolet.setVisibility(View.INVISIBLE);
                break;
            case BLANC:
                mDeBlanc.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void active_tous_des()
    {
        for (int i=0; i<DeActifs.length; i++)
            DeActifs[i]=1;

        mDeJaune.setVisibility(View.VISIBLE);
        mDeBleu.setVisibility(View.VISIBLE);
        mDeVert.setVisibility(View.VISIBLE);
        mDeOrange.setVisibility(View.VISIBLE);
        mDeViolet.setVisibility(View.VISIBLE);
        mDeBlanc.setVisibility(View.VISIBLE);
    }

    private void utilise_de_bleu(int valeurBleuEtBlanc) {
        ImageViewTresFute caseBleu=CasesBleues[valeurBleuEtBlanc-1];

        if (caseBleu.getValeur()!=0)
        {
            caseBleu.onClick(0);
            tableauClickBleu[caseBleu.getLigne()][caseBleu.getColonne()] = 0;
        }
        else
            affiche_message_non_autorise();
    }

    private void utilise_de_vert(int valeur) {
        int index, retour=0;
        for (index=1; index<tableauClickVert.length; index++)
        {
            if (tableauClickVert[index]!=0)
            {
                if (valeur<tableauClickVert[index])
                    retour = -1;
                break;
            }
        }

        System.out.println("utilise_de_vert : " + index + " - " + valeur + " - " + retour);

        if(retour == 0 && index < tableauClickVert.length) {
            tableauClickVert[index] = 0;
            CasesVertes[index].onClick(valeur);
        }
        else
            affiche_message_non_autorise();
    }

    private void utilise_de_orange(int valeur) {
        int index;
        for (index=1; index<tableauClickOrange.length; index++)
        {
            System.out.println("utilise_de_orange FOR " + index + " - " + tableauClickOrange[index]);
            if (tableauClickOrange[index]==0)
                break;
        }

        System.out.println("utilise_de_orange : " + index + " - " + valeur);

        if(index < tableauClickOrange.length) {
            tableauClickOrange[index] = valeur;
            CasesOranges[index].onClick(valeur);
        }
        else
            affiche_message_non_autorise();
    }

    private void utilise_de_violet(int valeur) {
        int index;
        for (index=1; index<tableauClickViolet.length; index++)
        {
            if (tableauClickViolet[index]==0)
                break;
        }

        System.out.println("utilise_de_violet : " + index + " - " + valeur);


        if(index < tableauClickViolet.length && valeur > tableauClickViolet[index-1]%6) {
            tableauClickViolet[index] = valeur;
            CasesViolettes[index].onClick(valeur);
        }
        else
            affiche_message_non_autorise();
    }

    private void affiche_message_non_autorise() {
        Toast.makeText(this, "Ce dé ne convient pas", Toast.LENGTH_SHORT).show();
    }

}