package com.pigredorou.topquizz.modele;

public class TresFuteTools {
    // Tableau avec les valeurs par défaut des cases
    public static int[][] tableauJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    public static int[][] tableauBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    public static int[] tableauVert = {-1,1,2,3,4,5,1,2,3,4,5,6};
    public static int[] tableauOrange = {0,0,0,0,0,0,0,0,0,0,0,0};
    public static int[] tableauViolet = {0,0,0,0,0,0,0,0,0,0,0,0};
    // Point selon les tableaux
    private static int[] tableauScoreColonneJaune = {10,14,16,20};
    private static int[] tableauScoreColonneBleu = {0,1,2,4,7,11,16,22,29,37,46,56};
    private static int[] tableauScoreColonneVert = {0,1,3,6,10,15,21,28,36,45,55,66};

    // Retourne 1 si la click sur cette case est possible, 0 sinon.
    public static int isClickPossibleCroix(int colonne, int valeurColonnePrec, int valeurColonneSuiv) {
        int clickOK = 0;

        switch (colonne) {
            case 1:
                if (valeurColonneSuiv > 0)
                    clickOK = 1;
                break;
            case 11:
                if (valeurColonnePrec == 0)
                    clickOK = 1;
                break;
            default:
                if (valeurColonnePrec == 0 && valeurColonneSuiv > 0)
                    clickOK = 1;
                break;
        }

        return clickOK ;
    }

    // Retourne 1 si la click sur cette case est possible, 0 sinon.
    public static int isClickPossibleChiffres(int colonne, int valeurColonnePrec, int valeurColonneSuiv) {
        int clickOK = 0;

        switch (colonne) {
            case 1:
                if (valeurColonneSuiv == 0)
                    clickOK = 1;
                break;
            case 11:
                if (valeurColonnePrec > 0)
                    clickOK = 1;
                break;
            default:
                if (valeurColonnePrec > 0 && valeurColonneSuiv == 0)
                    clickOK = 1;
                break;
        }

        return clickOK ;
    }

    public static int calcul_score_jaune(int[][] tableauClickJaune) {
        int colonneOK=1;
        //int ligneOK=1;
        int score=0;

        //System.out.println("TresFute:calcul_score_jaune : ");
        for (int index1 = 0; index1 < tableauClickJaune.length; index1++) {
            for (int index2 = 0; index2 < tableauClickJaune[index1].length; index2++) {
                //System.out.print(tableauClickJaune[index2][index1] + " ");
                if (tableauClickJaune[index2][index1] != 0) {
                    colonneOK = 0;
                    break;
                }
            }
            //System.out.println( " ");

            if (colonneOK == 1) {
                score += tableauScoreColonneJaune[index1];
                //System.out.println("COLONNE COMPLETE "+ tableauScoreColonneJaune[index1]);

            }

            colonneOK=1;
        }

        return score;
    }

    public static int calcul_score_bleu(int[][] tableauClickBleu) {
        int score;
        int nbCroix=0;

        for (int[] ints : tableauClickBleu) {
            for (int anInt : ints) {
                if (anInt == 0) {
                    nbCroix++;
                }
            }
        }
        score = tableauScoreColonneBleu[nbCroix];

        return score;
    }

    public static int calcul_score_vert(int[] tableauClickVert) {
        int score;
        int nbCroix=0;

        for (int ints : tableauClickVert) {
            if (ints == 0) {
                nbCroix++;
            }
        }
        score = tableauScoreColonneVert[nbCroix];
        return score;
    }

    public static int calcul_score_orange(int[] tableauOrange) {
        return calcul_score_a_point(tableauOrange);
    }

    public static int calcul_score_violet(int[] tableauViolet) {
        return calcul_score_a_point(tableauViolet);
    }

    private static int calcul_score_a_point(int[] tableauAPoints) {
        int score=0;

        for (int ints : tableauAPoints) {
            score+=ints;
        }
        return score;
    }
}
