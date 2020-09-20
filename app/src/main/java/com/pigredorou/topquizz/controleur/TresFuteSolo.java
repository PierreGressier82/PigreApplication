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

import java.util.Arrays;
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

public class TresFuteSolo extends AppCompatActivity implements View.OnClickListener {
    //public static int[][] tableauJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    //public static int[][] tableauBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    //public static int[] tableauVert = {-1,1,2,3,4,5,1,2,3,4,5,6};
    //public static int[] tableauOrange = {-1,0,0,0,0,0,0,0,0,0,0,0};
    //public static int[] tableauViolet = {-1,0,0,0,0,0,0,0,0,0,0,0};

    //private static int[] tableauScoreColonneJaune = {10,14,16,20};
    //private static int[] tableauScoreColonneBleu = {0,1,2,4,7,11,16,22,29,37,46,56};
    //private static int[] tableauScoreColonneVert = {0,1,3,6,10,15,21,28,36,45,55,66};

    private int[][] tableauClickJaune = {{3, 6, 5, 0}, {2, 1, 0, 5}, {1, 0, 2, 4}, {0, 3, 4, 6}};
    private int[][] tableauClickBleu = {{1, 1, 1, 1}, {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    private int[] tableauClickVert = tableauVert.clone();
    private int[] tableauClickOrange = tableauOrange.clone();
    private int[] tableauClickViolet = tableauViolet.clone();

    private ImageViewTresFute[][] CasesJaunes = new ImageViewTresFute[3][7]; // Première ligne et première colonne non utilisées
    private ImageViewTresFute[] CasesBleues = new ImageViewTresFute[12];
    private ImageViewTresFute[] CasesVertes = new ImageViewTresFute[12];
    private ImageViewTresFute[] CasesOranges = new ImageViewTresFute[12];
    private ImageViewTresFute[] CasesViolettes = new ImageViewTresFute[12];

    private int[] DeActifs = {1, 1, 1, 1, 1, 1}; // Tous les dés sont actifs par defaut
    private int[] ValeurDes = {0, 0, 0, 0, 0, 0};
    //private int[] ValeurDesPlateau = {0, 0, 0, 0, 0, 0};
    //private int[] tourDeJeuValeur = new int[3]; // Valeur des dés par tour de jeu
    //private int[] tourDeJeuCouleur = new int[3]; // Couleur des dés choisis par tour de jeu
    private int LanceDe = 1;
    private int tour = 1;

    private int[] couleursRessource = new int[6];

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
    private TextView mDeJaunePlateau;
    private TextView mDeBleuPlateau;
    private TextView mDeVertPlateau;
    private TextView mDeOrangePlateau;
    private TextView mDeVioletPlateau;
    private TextView mDeBlancPlateau;

    private TextView mDeLance1;
    private TextView mDeLance2;
    private TextView mDeLance3;

    private ImageView mTour2;
    private ImageView mTour3;
    private ImageView mTour4;
    private ImageView mTour5;
    private ImageView mTour6;


    private boolean desLances = false;
    private boolean dePlateauAChoisir = false;
    private boolean caseAChoisir = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Remet la mise en veille en place
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

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

        // Bloque la mise en veille
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_tres_fute_solo);

        // Chargement des élements du XML
        // Tableaux
        mscoreJaune = findViewById(R.id.score_jaune);
        mscoreBleu = findViewById(R.id.score_bleu);
        mscoreVert = findViewById(R.id.score_vert);
        mscoreOrange = findViewById(R.id.score_orange);
        mscoreViolet = findViewById(R.id.score_violet);
        mscoreRouge = findViewById(R.id.score_rouge);
        mscoreTotal = findViewById(R.id.score_total);

        // Dés
        mDeJaune = findViewById(R.id.de_jaune);
        mDeBleu = findViewById(R.id.de_bleu);
        mDeVert = findViewById(R.id.de_vert);
        mDeOrange = findViewById(R.id.de_orange);
        mDeViolet = findViewById(R.id.de_violet);
        mDeBlanc = findViewById(R.id.de_blanc);
        mDeJaunePlateau = findViewById(R.id.de_jaune_plateau);
        mDeBleuPlateau = findViewById(R.id.de_bleu_plateau);
        mDeVertPlateau = findViewById(R.id.de_vert_plateau);
        mDeOrangePlateau = findViewById(R.id.de_orange_plateau);
        mDeVioletPlateau = findViewById(R.id.de_violet_plateau);
        mDeBlancPlateau = findViewById(R.id.de_blanc_plateau);

        mDeJaune.setOnClickListener(this);
        mDeBleu.setOnClickListener(this);
        mDeVert.setOnClickListener(this);
        mDeOrange.setOnClickListener(this);
        mDeViolet.setOnClickListener(this);
        mDeBlanc.setOnClickListener(this);
        mDeJaunePlateau.setOnClickListener(this);
        mDeBleuPlateau.setOnClickListener(this);
        mDeVertPlateau.setOnClickListener(this);
        mDeOrangePlateau.setOnClickListener(this);
        mDeVioletPlateau.setOnClickListener(this);
        mDeBlancPlateau.setOnClickListener(this);

        mDeJaune.setTag("DeJaune");
        mDeBleu.setTag("DeBleu");
        mDeVert.setTag("DeVert");
        mDeOrange.setTag("DeOrange");
        mDeViolet.setTag("DeViolet");
        mDeBlanc.setTag("DeBlanc");
        mDeJaunePlateau.setTag("PlateauDeJaune");
        mDeBleuPlateau.setTag("PlateauDeBleu");
        mDeVertPlateau.setTag("PlateauDeVert");
        mDeOrangePlateau.setTag("PlateauDeOrange");
        mDeVioletPlateau.setTag("PlateauDeViolet");
        mDeBlancPlateau.setTag("PlateauDeBlanc");

        // Tour de jeu
        mDeLance1 = findViewById(R.id.de_lance_1);
        mDeLance2 = findViewById(R.id.de_lance_2);
        mDeLance3 = findViewById(R.id.de_lance_3);

        // Manches
        mTour2 = findViewById(R.id.tour_2);
        mTour3 = findViewById(R.id.tour_3);
        mTour4 = findViewById(R.id.tour_4);
        mTour5 = findViewById(R.id.tour_5);
        mTour6 = findViewById(R.id.tour_6);

        mTour2.setVisibility(View.INVISIBLE);
        mTour3.setVisibility(View.INVISIBLE);
        mTour4.setVisibility(View.INVISIBLE);
        mTour5.setVisibility(View.INVISIBLE);
        mTour6.setVisibility(View.INVISIBLE);

        // Boutons
        ImageView mLanceDes = findViewById(R.id.lance_des);
        ImageView mRAZ = findViewById(R.id.raz);
        ImageView quitter = findViewById(R.id.exit);
        mLanceDes.setOnClickListener(this);
        mRAZ.setOnClickListener(this);
        quitter.setOnClickListener(this);
        mLanceDes.setTag("LancerDes");
        mRAZ.setTag("RAZ");
        quitter.setTag("Quitter");

        // Couleurs
        init_couleurs_ressources();

        // Definition des cases des tableaux de couleur
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

        CasesJaunes[1][1] = imageJauneLigne3Case1;
        CasesJaunes[2][1] = imageJauneLigne2Case2;
        CasesJaunes[1][2] = imageJauneLigne2Case1;
        CasesJaunes[2][2] = imageJauneLigne3Case3;
        CasesJaunes[1][3] = imageJauneLigne1Case1;
        CasesJaunes[2][3] = imageJauneLigne4Case2;
        CasesJaunes[1][4] = imageJauneLigne3Case4;
        CasesJaunes[2][4] = imageJauneLigne4Case3;
        CasesJaunes[1][5] = imageJauneLigne1Case3;
        CasesJaunes[2][5] = imageJauneLigne2Case4;
        CasesJaunes[1][6] = imageJauneLigne1Case2;
        CasesJaunes[2][6] = imageJauneLigne4Case4;

        for (int i = 1; i < CasesJaunes.length; i++)
            for (int j = 1; j < CasesJaunes[i].length; j++) {
                System.out.println("CASES JAUNES " + i + " " + j);
                CasesJaunes[i][j].setCouleur(JAUNE);
                CasesJaunes[i][j].setOnClickListener(this);
                CasesJaunes[i][j].setClickable(false);
            }

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

        // Affichage du bonus renard pour score rouge
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

        CasesBleues[1] = imageBleuLigne2Case2;
        CasesBleues[2] = imageBleuLigne2Case3;
        CasesBleues[3] = imageBleuLigne2Case4;
        CasesBleues[4] = imageBleuLigne3Case1;
        CasesBleues[5] = imageBleuLigne3Case2;
        CasesBleues[6] = imageBleuLigne3Case3;
        CasesBleues[7] = imageBleuLigne3Case4;
        CasesBleues[8] = imageBleuLigne4Case1;
        CasesBleues[9] = imageBleuLigne4Case2;
        CasesBleues[10] = imageBleuLigne4Case3;
        CasesBleues[11] = imageBleuLigne4Case4;

        for (int i = 1; i < CasesBleues.length; i++) {
            int ligne = (i / 4) + 1;
            CasesBleues[i].setCouleur(BLEU);
            CasesBleues[i].setLigne(ligne); // Partie entière de la division => +1 car on commence en ligne 2
            CasesBleues[i].setColonne(i % 4); // Modulo => Reste de la division
            CasesBleues[i].setOnClickListener(this);
            CasesBleues[i].setClickable(false);
            System.out.println("BLEU - lig:" + ligne + " col:" + i % 4);
        }

        // Affichage du bonus renard pour score rouge
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

        CasesVertes[1] = imageVertLigne1Case2;
        CasesVertes[2] = imageVertLigne1Case3;
        CasesVertes[3] = imageVertLigne1Case4;
        CasesVertes[4] = imageVertLigne1Case5;
        CasesVertes[5] = imageVertLigne1Case6;
        CasesVertes[6] = imageVertLigne1Case7;
        CasesVertes[7] = imageVertLigne1Case8;
        CasesVertes[8] = imageVertLigne1Case9;
        CasesVertes[9] = imageVertLigne1Case10;
        CasesVertes[10] = imageVertLigne1Case11;
        CasesVertes[11] = imageVertLigne1Case12;

        for (int i = 1; i < CasesVertes.length; i++) {
            CasesVertes[i].setColonne(i);
            CasesVertes[i].setCouleur(VERT);
            CasesVertes[i].setOnClickListener(this);
            CasesVertes[i].setClickable(false);
        }
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

        CasesOranges[1] = imageOrangeLigne1Case2;
        CasesOranges[2] = imageOrangeLigne1Case3;
        CasesOranges[3] = imageOrangeLigne1Case4;
        CasesOranges[4] = imageOrangeLigne1Case5;
        CasesOranges[5] = imageOrangeLigne1Case6;
        CasesOranges[6] = imageOrangeLigne1Case7;
        CasesOranges[7] = imageOrangeLigne1Case8;
        CasesOranges[8] = imageOrangeLigne1Case9;
        CasesOranges[9] = imageOrangeLigne1Case10;
        CasesOranges[10] = imageOrangeLigne1Case11;
        CasesOranges[11] = imageOrangeLigne1Case12;

        for (int i = 1; i < CasesOranges.length; i++) {
            CasesOranges[i].setColonne(i);
            CasesOranges[i].setCouleur(ORANGE);
            CasesOranges[i].setOnClickListener(this);
            CasesOranges[i].setClickable(false);
        }
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

        CasesViolettes[1] = imageVioletLigne1Case2;
        CasesViolettes[2] = imageVioletLigne1Case3;
        CasesViolettes[3] = imageVioletLigne1Case4;
        CasesViolettes[4] = imageVioletLigne1Case5;
        CasesViolettes[5] = imageVioletLigne1Case6;
        CasesViolettes[6] = imageVioletLigne1Case7;
        CasesViolettes[7] = imageVioletLigne1Case8;
        CasesViolettes[8] = imageVioletLigne1Case9;
        CasesViolettes[9] = imageVioletLigne1Case10;
        CasesViolettes[10] = imageVioletLigne1Case11;
        CasesViolettes[11] = imageVioletLigne1Case12;

        for (int i = 1; i < CasesViolettes.length; i++) {
            CasesViolettes[i].setColonne(i);
            CasesViolettes[i].setCouleur(VIOLET);
            CasesViolettes[i].setOnClickListener(this);
            CasesViolettes[i].setClickable(false);
        }
    }

    private void init_couleurs_ressources() {
        couleursRessource[BLANC] = getResources().getColor(R.color.blanc);
        couleursRessource[JAUNE] = getResources().getColor(R.color.jaune);
        couleursRessource[BLEU] = getResources().getColor(R.color.bleu);
        couleursRessource[VERT] = getResources().getColor(R.color.vert);
        couleursRessource[ORANGE] = getResources().getColor(R.color.orange);
        couleursRessource[VIOLET] = getResources().getColor(R.color.violet);
    }

    @Override
    public void onClick(View v) {
        if (v.getClass().toString().endsWith("ImageViewTresFute")) {
            int colonne;

            ImageViewTresFute imageClick = findViewById(v.getId());
            colonne = imageClick.getColonne();

            switch (imageClick.getCouleur()) {
                case JAUNE:
                    imageClick.onClick(0);
                    tableauClickJaune[imageClick.getLigne()][colonne] = imageClick.getValeur();
                    break;
                case BLEU:
                    imageClick.onClick(0);
                    tableauClickBleu[imageClick.getLigne()][colonne] = imageClick.getValeur();
                    break;
                case VERT:
                    imageClick.onClick(ValeurDes[BLANC]);
                    tableauClickVert[colonne] = imageClick.getValeur();
                    break;
                case ORANGE:
                    imageClick.onClick(ValeurDes[BLANC]);
                    tableauClickOrange[colonne] = imageClick.getValeur();
                    break;
                case VIOLET:
                    imageClick.onClick(ValeurDes[BLANC]);
                    tableauClickViolet[colonne] = imageClick.getValeur();
                    break;
            }
            stop_click();
            caseAChoisir = false;
        }

        // Choix d'une dé
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("De")) {
            if (!desLances)
                Toast.makeText(this, "Il faut relancer les dés !", Toast.LENGTH_SHORT).show();
            else {
                boolean deOK = false;
                switch (v.getTag().toString()) {
                    case "DeBlanc":
                        deOK = utilise_de_blanc(ValeurDes[BLANC], ValeurDes[BLEU]);
                        if (deOK)
                            desactive_de(BLANC);
                        break;
                    case "DeJaune":
                        deOK = utilise_de_jaune(ValeurDes[JAUNE]);
                        if (deOK)
                            desactive_de(JAUNE);
                        break;
                    case "DeBleu":
                        deOK = utilise_de_bleu(ValeurDes[BLEU] + ValeurDes[BLANC]);
                        if (deOK)
                            desactive_de(BLEU);
                        break;
                    case "DeVert":
                        deOK = utilise_de_vert(ValeurDes[VERT]);
                        if (deOK)
                            desactive_de(VERT);
                        break;
                    case "DeOrange":
                        deOK = utilise_de_orange(ValeurDes[ORANGE]);
                        if (deOK)
                            desactive_de(ORANGE);
                        break;
                    case "DeViolet":
                        deOK = utilise_de_violet(ValeurDes[VIOLET]);
                        if (deOK)
                            desactive_de(VIOLET);
                        break;
                }
                if (deOK) {
                    LanceDe++;
                }
                // Si dernier tour de jeu, on place tous les dés sur le plateau
                if (LanceDe == 4) {
                    retire_des_inferieurs_vers_plateau(7);
                    dePlateauAChoisir = true;
                }
            }
        }

        // Choix d'une dé sur le plateau
        if (v.getClass().toString().endsWith("TextView") && v.getTag().toString().startsWith("Plateau")) {
            if (!dePlateauAChoisir)
                Toast.makeText(this, "Dé plateau possible uniquement en fin de tour", Toast.LENGTH_SHORT).show();
            else {
                boolean deOK = false;
                switch (v.getTag().toString()) {
                    case "PlateauDeBlanc":
                        deOK = utilise_de_blanc(ValeurDes[BLANC], ValeurDes[BLEU]);
                        break;
                    case "PlateauDeJaune":
                        deOK = utilise_de_jaune(ValeurDes[JAUNE]);
                        break;
                    case "PlateauDeBleu":
                        deOK = utilise_de_bleu(ValeurDes[BLEU] + ValeurDes[BLANC]);
                        break;
                    case "PlateauDeVert":
                        deOK = utilise_de_vert(ValeurDes[VERT]);
                        break;
                    case "PlateauDeOrange":
                        deOK = utilise_de_orange(ValeurDes[ORANGE]);
                        break;
                    case "PlateauDeViolet":
                        deOK = utilise_de_violet(ValeurDes[VIOLET]);
                        break;
                }
                if (deOK)
                    dePlateauAChoisir = false;
            }
        }

        // Bouton pour lancer les dés
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("LancerDes")) {
            if (desLances)
                Toast.makeText(this, "Il faut choisir un dé !", Toast.LENGTH_SHORT).show();
            else if (dePlateauAChoisir)
                Toast.makeText(this, "Il faut choisir un dé du plateau !", Toast.LENGTH_SHORT).show();
            else if (caseAChoisir)
                Toast.makeText(this, "Il faut cocher une case !", Toast.LENGTH_SHORT).show();
            else {
                if (tour == 7)
                    Toast.makeText(this, "Partie terminé - Score " + mscoreTotal.getText(), Toast.LENGTH_SHORT).show();
                else {
                    if (LanceDe == 4) {
                        active_tous_des();
                        LanceDe = 1;
                        affiche_tour_suivant(++tour);
                    }
                    if (tour == 7)
                        Toast.makeText(this, "Partie terminé - Score " + mscoreTotal.getText(), Toast.LENGTH_SHORT).show();
                    else {
                        lance_des();
                        desLances = true;
                    }
                }
            }
        }
        // Bouton pour passer son tour (si choix impossible)
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("RAZ")) {
            if (LanceDe < 4) {
                LanceDe++;
                desLances = false;
                dePlateauAChoisir = true;
            }
            else
                dePlateauAChoisir = false;
        }
        // Bouton pour quitter
        if (v.getClass().toString().endsWith("ImageView") && v.getTag().toString().equals("Quitter"))
            finish();
        calcul_score_total();
    }

    private void affiche_tour_suivant(int manche) {
        System.out.println("affiche_manche_suivante : " + manche);
        switch (manche) {
            case 2:
                mTour2.setVisibility(View.VISIBLE);
                break;
            case 3:
                mTour3.setVisibility(View.VISIBLE);
                break;
            case 4:
                mTour4.setVisibility(View.VISIBLE);
                break;
            case 5:
                mTour5.setVisibility(View.VISIBLE);
                break;
            case 6:
                mTour6.setVisibility(View.VISIBLE);
                break;
        }
    }

    private int calcul_score_rouge(int scoreMin) {
        int score = 0;

        if (tableauClickJaune[3][1] == 0 && tableauClickJaune[3][2] == 0 && tableauClickJaune[3][3] == 0) {
            score++;
            imageJauneLigne4Case5.setVisibility(View.VISIBLE);
        } else
            imageJauneLigne4Case5.setVisibility(View.INVISIBLE);

        if (tableauClickBleu[3][0] == 0 && tableauClickBleu[3][1] == 0 && tableauClickBleu[3][2] == 0 && tableauClickBleu[3][3] == 0) {
            score++;
            imageBleuLigne4Case5.setVisibility(View.VISIBLE);
        } else
            imageBleuLigne4Case5.setVisibility(View.INVISIBLE);

        if (tableauClickVert[7] == 0)
            score++;
        if (tableauClickOrange[8] != 0)
            score++;
        if (tableauClickViolet[7] != 0)
            score++;

        score *= scoreMin;
        mscoreRouge.setText(String.valueOf(score));
        return score;
    }

    private void calcul_score_total() {
        int score;
        int scoreJaune;
        int scoreBleu;
        int scoreVert;
        int scoreOrange;
        int scoreViolet;
        int scoreMin;
        String texteScore;

        // Jaune
        scoreJaune = TresFuteTools.calcul_score_jaune(tableauClickJaune);
        mscoreJaune.setText(String.valueOf(scoreJaune));

        scoreMin = scoreJaune;

        // Bleu
        scoreBleu = TresFuteTools.calcul_score_bleu(tableauClickBleu);
        mscoreBleu.setText(String.valueOf(scoreBleu));
        if (scoreBleu < scoreMin)
            scoreMin = scoreBleu;

        // Vert
        scoreVert = TresFuteTools.calcul_score_vert(tableauClickVert);
        mscoreVert.setText(String.valueOf(scoreVert));
        if (scoreVert < scoreMin)
            scoreMin = scoreVert;

        // Orange
        scoreOrange = TresFuteTools.calcul_score_orange(tableauClickOrange);
        mscoreOrange.setText(String.valueOf(scoreOrange));
        if (scoreOrange < scoreMin)
            scoreMin = scoreOrange;

        // Violet
        scoreViolet = TresFuteTools.calcul_score_violet(tableauClickViolet);
        mscoreViolet.setText(String.valueOf(scoreViolet));

        if (scoreViolet < scoreMin)
            scoreMin = scoreViolet;

        score = scoreJaune + scoreBleu + scoreVert + scoreOrange + scoreViolet + (calcul_score_rouge(scoreMin));

        texteScore = getString(R.string.Score) + " " + score;
        mscoreTotal.setText(texteScore);
    }

    private void lance_des() {
        System.out.println("DEBUT LANCE_DES");

        if (DeActifs[BLANC] == 1) {
            ValeurDes[BLANC] = lance_de();
            mDeBlanc.setText(String.valueOf(ValeurDes[BLANC]));
        }
        if (DeActifs[JAUNE] == 1) {
            ValeurDes[JAUNE] = lance_de();
            mDeJaune.setText(String.valueOf(ValeurDes[JAUNE]));
        }
        if (DeActifs[BLEU] == 1) {
            ValeurDes[BLEU] = lance_de();
            mDeBleu.setText(String.valueOf(ValeurDes[BLEU]));
        }
        if (DeActifs[VERT] == 1) {
            ValeurDes[VERT] = lance_de();
            mDeVert.setText(String.valueOf(ValeurDes[VERT]));
        }
        if (DeActifs[ORANGE] == 1) {
            ValeurDes[ORANGE] = lance_de();
            mDeOrange.setText(String.valueOf(ValeurDes[ORANGE]));
        }
        if (DeActifs[VIOLET] == 1) {
            ValeurDes[VIOLET] = lance_de();
            mDeViolet.setText(String.valueOf(ValeurDes[VIOLET]));
        }

        System.out.println("FIN LANCE_DES");
    }

    private int lance_de() {
        int nbAleatoire;
        // génération d'un entier >= 1 et <= 6
        nbAleatoire = (int) (Math.random() * 6) + 1;

        System.out.println("Resulat DE : " + nbAleatoire);

        return nbAleatoire;
    }

    private void desactive_de(int couleur) {
        DeActifs[couleur] = 0;

        masque_de(couleur);

        // Mise de côte du dé
        switch (LanceDe) {
            case 1:
                mDeLance1.setText(String.valueOf(ValeurDes[couleur]));
                mDeLance1.setTextColor(couleursRessource[couleur]);
                break;
            case 2:
                mDeLance2.setText(String.valueOf(ValeurDes[couleur]));
                mDeLance2.setTextColor(couleursRessource[couleur]);
                break;
            case 3:
                mDeLance3.setText(String.valueOf(ValeurDes[couleur]));
                mDeLance3.setTextColor(couleursRessource[couleur]);
                break;
        }

        // On retire les dés de valeurs inférieurs
        retire_des_inferieurs_vers_plateau(ValeurDes[couleur]);

        desLances = false;
    }

    private void masque_de(int couleur) {
        switch (couleur) {
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

    private void stop_click() {
        for (int i = 1; i < CasesJaunes.length; i++)
            for (int j = 1; j < CasesJaunes[i].length; j++) {
                CasesJaunes[i][j].setClickable(false);
            }
        for (int i = 1; i < CasesBleues.length; i++)
            CasesBleues[i].setClickable(false);
        for (int i = 1; i < CasesVertes.length; i++)
            CasesVertes[i].setClickable(false);
        for (int i = 1; i < CasesOranges.length; i++)
            CasesOranges[i].setClickable(false);
        for (int i = 1; i < CasesViolettes.length; i++)
            CasesViolettes[i].setClickable(false);
    }

    private void affiche_de_plateau(int couleur) {
        switch (couleur) {
            case JAUNE:
                mDeJaunePlateau.setText(String.valueOf(ValeurDes[couleur]));
                break;
            case BLEU:
                mDeBleuPlateau.setText(String.valueOf(ValeurDes[couleur]));
                break;
            case VERT:
                mDeVertPlateau.setText(String.valueOf(ValeurDes[couleur]));
                break;
            case ORANGE:
                mDeOrangePlateau.setText(String.valueOf(ValeurDes[couleur]));
                break;
            case VIOLET:
                mDeVioletPlateau.setText(String.valueOf(ValeurDes[couleur]));
                break;
            case BLANC:
                mDeBlancPlateau.setText(String.valueOf(ValeurDes[couleur]));
                break;
        }
        //ValeurDesPlateau[couleur] = ValeurDes[couleur];
    }

    private void retire_des_inferieurs_vers_plateau(int valeurDe) {
        for (int i = 0; i < DeActifs.length; i++) {
            if (DeActifs[i] == 1 && ValeurDes[i] < valeurDe) {
                deplace_de(i);
                DeActifs[i] = 0;
                //ValeurDesPlateau[i] = ValeurDes[i];
            }
        }
    }

    private void deplace_de(int couleur) {
        masque_de(couleur);
        affiche_de_plateau(couleur);
    }

    private void active_tous_des() {
        Arrays.fill(DeActifs, 1);
        //Arrays.fill(ValeurDesPlateau, 0);

        mDeJaune.setVisibility(View.VISIBLE);
        mDeBleu.setVisibility(View.VISIBLE);
        mDeVert.setVisibility(View.VISIBLE);
        mDeOrange.setVisibility(View.VISIBLE);
        mDeViolet.setVisibility(View.VISIBLE);
        mDeBlanc.setVisibility(View.VISIBLE);

        mDeLance1.setText("");
        mDeLance2.setText("");
        mDeLance3.setText("");

        mDeJaunePlateau.setText("");
        mDeBleuPlateau.setText("");
        mDeVertPlateau.setText("");
        mDeOrangePlateau.setText("");
        mDeVioletPlateau.setText("");
        mDeBlancPlateau.setText("");
    }

    // Rendre clicable toutes les cases possibles
    private boolean utilise_de_blanc(int valeurBlanc, int valeurBleu) {
        int nbCases = 0;
        int index;

        // Jaune
        ImageViewTresFute caseJaune1 = CasesJaunes[1][valeurBlanc];
        ImageViewTresFute caseJaune2 = CasesJaunes[2][valeurBlanc];
        if (caseJaune1.getValeur() != 0) {
            caseJaune1.setClickable(true);
            nbCases++;
        }
        if (caseJaune2.getValeur() != 0) {
            caseJaune2.setClickable(true);
            nbCases++;
        }
        // Bleu
        ImageViewTresFute caseBleu = CasesBleues[valeurBleu + valeurBlanc - 1];
        if (caseBleu.getValeur() != 0) {
            caseBleu.setClickable(true);
            nbCases++;
        }
        // Vert
        for (index = 1; index < tableauClickVert.length; index++) {
            if (tableauClickVert[index] != 0)
                break;
        }
        if (index < tableauClickVert.length && valeurBlanc >= tableauClickVert[index]) {
            CasesVertes[index].setClickable(true);
            nbCases++;
        }
        // Orange
        for (index = 1; index < tableauClickOrange.length; index++) {
            if (tableauClickOrange[index] == 0)
                break;
        }
        if (index < tableauClickOrange.length) {
            CasesOranges[index].setClickable(true);
            nbCases++;
        }
        // Violet
        for (index = 1; index < tableauClickViolet.length; index++) {
            if (tableauClickViolet[index] == 0)
                break;
        }
        if (index < tableauClickViolet.length && valeurBlanc > tableauClickViolet[index - 1] % 6) {
            CasesViolettes[index].setClickable(true);
            nbCases++;
        }

        if (nbCases == 0)
            affiche_message_non_autorise();
        else {
            caseAChoisir = true;
        }

        return caseAChoisir;
    }

    private boolean utilise_de_jaune(int valeur) {
        boolean retour = false;
        int nbCases = 0;
        // Rendre clicable les cases jaunes possibles
        ImageViewTresFute caseJaune1 = CasesJaunes[1][valeur];
        ImageViewTresFute caseJaune2 = CasesJaunes[2][valeur];

        if (caseJaune1.getValeur() != 0) {
            nbCases++;
            retour = true;
        }
        if (caseJaune2.getValeur() != 0) {
            nbCases += 2;
            retour = true;
        }

        // Si 1 seule case possible, on la coche par defaut, sinon, on laisse le choix
        switch (nbCases) {
            case 0:
                affiche_message_non_autorise();
                break;
            case 1:
                caseJaune1.onClick(valeur);
                tableauClickJaune[caseJaune1.getLigne()][caseJaune1.getColonne()] = caseJaune1.getValeur();
                break;
            case 2:
                caseJaune2.onClick(valeur);
                tableauClickJaune[caseJaune2.getLigne()][caseJaune2.getColonne()] = caseJaune2.getValeur();
                break;
            default:
                caseJaune1.setClickable(true);
                caseJaune2.setClickable(true);
                caseAChoisir = true;
                break;
        }

        return retour;
    }

    private boolean utilise_de_bleu(int valeurBleuEtBlanc) {
        ImageViewTresFute caseBleu = CasesBleues[valeurBleuEtBlanc - 1];
        boolean retour = false;

        if (caseBleu.getValeur() != 0) {
            caseBleu.onClick(0);
            tableauClickBleu[caseBleu.getLigne()][caseBleu.getColonne()] = 0;
            retour = true;
        } else
            affiche_message_non_autorise();

        return retour;
    }

    private boolean utilise_de_vert(int valeur) {
        int index;
        boolean retour = true;
        for (index = 1; index < tableauClickVert.length; index++) {
            if (tableauClickVert[index] != 0) {
                if (valeur < tableauClickVert[index])
                    retour = false;
                break;
            }
        }

        System.out.println("utilise_de_vert : " + index + " - " + valeur + " - " + retour);

        if (retour && index < tableauClickVert.length) {
            tableauClickVert[index] = 0;
            CasesVertes[index].onClick(valeur);
        } else
            affiche_message_non_autorise();

        return retour;
    }

    private boolean utilise_de_orange(int valeur) {
        int index;
        boolean retour = false;
        for (index = 1; index < tableauClickOrange.length; index++) {
            System.out.println("utilise_de_orange FOR " + index + " - " + tableauClickOrange[index]);
            if (tableauClickOrange[index] == 0)
                break;
        }

        System.out.println("utilise_de_orange : " + index + " - " + valeur);

        if (index < tableauClickOrange.length) {
            tableauClickOrange[index] = valeur;
            CasesOranges[index].onClick(valeur);
            retour = true;
        } else
            affiche_message_non_autorise();

        return retour;
    }

    private boolean utilise_de_violet(int valeur) {
        int index;
        boolean retour = false;
        for (index = 1; index < tableauClickViolet.length; index++) {
            if (tableauClickViolet[index] == 0)
                break;
        }

        System.out.println("utilise_de_violet : " + index + " - " + valeur);


        if (index < tableauClickViolet.length && valeur > tableauClickViolet[index - 1] % 6) {
            tableauClickViolet[index] = valeur;
            CasesViolettes[index].onClick(valeur);
            retour = true;
        } else
            affiche_message_non_autorise();

        return retour;
    }

    private void affiche_message_non_autorise() {
        Toast.makeText(this, "Ce dé ne convient pas", Toast.LENGTH_SHORT).show();
    }

}