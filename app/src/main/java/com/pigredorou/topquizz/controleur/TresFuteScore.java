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

import static com.pigredorou.topquizz.modele.ImageViewTresFute.BLEU;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.JAUNE;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.ORANGE;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.VERT;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.VIOLET;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.tableauOrange;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.tableauVert;
import static com.pigredorou.topquizz.modele.ImageViewTresFute.tableauViolet;

public class TresFuteScore extends AppCompatActivity implements View.OnClickListener
{
    //public static int[][] tableauJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    //public static int[][] tableauBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    //public static int[] tableauVert = {-1,1,2,3,4,5,1,2,3,4,5,6};
    //public static int[] tableauOrange = {0,0,0,0,0,0,0,0,0,0,0,0};
    //public static int[] tableauViolet = {0,0,0,0,0,0,0,0,0,0,0,0};

    // Etat de la feuille de jeu
    private int[][] tableauClickJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    private int[][] tableauClickBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    private int[] tableauClickVert = tableauVert.clone();
    private int[] tableauClickOrange = tableauOrange.clone();
    private int[] tableauClickViolet = tableauViolet.clone();

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

    private ImageView mRAZ;
    private ImageView mUndo;
    private ImageView mExit;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("TresFute::onCreate()");

        // Masque le bar de titre de l'activité
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Passe l'appli en plein écran et cache la barre de status.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Lancement forcé en mode paysage
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        setContentView(R.layout.activity_tres_fute_score);

        // Chargement des élements du XML
        // TABLEAUX
        mscoreJaune = findViewById(R.id.score_jaune);
        mscoreBleu = findViewById(R.id.score_bleu);
        mscoreVert = findViewById(R.id.score_vert);
        mscoreOrange = findViewById(R.id.score_orange);
        mscoreViolet = findViewById(R.id.score_violet);
        mscoreRouge = findViewById(R.id.score_rouge);
        mscoreTotal = findViewById(R.id.score_total);

        // Boutons
        mRAZ = findViewById(R.id.raz);
        mUndo = findViewById(R.id.undo);
        mExit = findViewById(R.id.exit);
        mRAZ.setOnClickListener(this);
        mUndo.setOnClickListener(this);
        mExit.setOnClickListener(this);
        mRAZ.setTag("raz");
        mUndo.setTag("undo");
        mExit.setTag("exit");

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

        imageJauneLigne1Case1.setOnClickListener(this);
        imageJauneLigne1Case2.setOnClickListener(this);
        imageJauneLigne1Case3.setOnClickListener(this);
        imageJauneLigne2Case1.setOnClickListener(this);
        imageJauneLigne2Case2.setOnClickListener(this);
        imageJauneLigne2Case4.setOnClickListener(this);
        imageJauneLigne3Case1.setOnClickListener(this);
        imageJauneLigne3Case3.setOnClickListener(this);
        imageJauneLigne3Case4.setOnClickListener(this);
        imageJauneLigne4Case2.setOnClickListener(this);
        imageJauneLigne4Case3.setOnClickListener(this);
        imageJauneLigne4Case4.setOnClickListener(this);

        // Image renard, masqué
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

        imageBleuLigne2Case2.setOnClickListener(this);
        imageBleuLigne2Case3.setOnClickListener(this);
        imageBleuLigne2Case4.setOnClickListener(this);
        imageBleuLigne3Case1.setOnClickListener(this);
        imageBleuLigne3Case2.setOnClickListener(this);
        imageBleuLigne3Case3.setOnClickListener(this);
        imageBleuLigne3Case4.setOnClickListener(this);
        imageBleuLigne4Case1.setOnClickListener(this);
        imageBleuLigne4Case2.setOnClickListener(this);
        imageBleuLigne4Case3.setOnClickListener(this);
        imageBleuLigne4Case4.setOnClickListener(this);

        // Image renard, masqué
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

        imageVertLigne1Case2.setOnClickListener(this);
        imageVertLigne1Case3.setOnClickListener(this);
        imageVertLigne1Case4.setOnClickListener(this);
        imageVertLigne1Case5.setOnClickListener(this);
        imageVertLigne1Case6.setOnClickListener(this);
        imageVertLigne1Case7.setOnClickListener(this);
        imageVertLigne1Case8.setOnClickListener(this);
        imageVertLigne1Case9.setOnClickListener(this);
        imageVertLigne1Case10.setOnClickListener(this);
        imageVertLigne1Case11.setOnClickListener(this);
        imageVertLigne1Case12.setOnClickListener(this);
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

        imageOrangeLigne1Case2.setOnClickListener(this);
        imageOrangeLigne1Case3.setOnClickListener(this);
        imageOrangeLigne1Case4.setOnClickListener(this);
        imageOrangeLigne1Case5.setOnClickListener(this);
        imageOrangeLigne1Case6.setOnClickListener(this);
        imageOrangeLigne1Case7.setOnClickListener(this);
        imageOrangeLigne1Case8.setOnClickListener(this);
        imageOrangeLigne1Case9.setOnClickListener(this);
        imageOrangeLigne1Case10.setOnClickListener(this);
        imageOrangeLigne1Case11.setOnClickListener(this);
        imageOrangeLigne1Case12.setOnClickListener(this);
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

        imageVioletLigne1Case2.setOnClickListener(this);
        imageVioletLigne1Case3.setOnClickListener(this);
        imageVioletLigne1Case4.setOnClickListener(this);
        imageVioletLigne1Case5.setOnClickListener(this);
        imageVioletLigne1Case6.setOnClickListener(this);
        imageVioletLigne1Case7.setOnClickListener(this);
        imageVioletLigne1Case8.setOnClickListener(this);
        imageVioletLigne1Case9.setOnClickListener(this);
        imageVioletLigne1Case10.setOnClickListener(this);
        imageVioletLigne1Case11.setOnClickListener(this);
        imageVioletLigne1Case12.setOnClickListener(this);
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
                        imageClick.onClick(modulo6);
                        tableauClickViolet[imageClick.getColonne()] = imageClick.getValeur();
                    }
                    break;
            }
            calcul_score_total();
        }

        // Undo
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("undo"))
            undo();
        // Reset - RAZ
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("raz"))
            raz();
        // Quitter
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("exit"))
            finish();
    }

    private void raz() {
        Toast.makeText(this, "En cours de développement", Toast.LENGTH_SHORT).show();

        // RAZ des "tableauclick.."
        // RAZ des valeurs des ImageViewTresFute
        // Mise à jour de l'affichage
        // Mise à jour du score
    }

    private void undo() {
        Toast.makeText(this, "En cours de développement", Toast.LENGTH_SHORT).show();

        // Créer un tableau ImageViewTresFute avec la liste des click
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
        scoreJaune=TresFuteTools.calcul_score_jaune(tableauClickJaune);
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
}