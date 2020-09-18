package com.pigredorou.topquizz.modele;

import android.content.Context;
import android.util.AttributeSet;

import com.pigredorou.topquizz.R;

public class ImageViewTresFute extends androidx.appcompat.widget.AppCompatImageView //implements View.OnClickListener
{
    // Tableau avec les valeurs par défaut des cases
    public static int[][] tableauJaune = {{3,6,5,0},{2,1,0,5},{1,0,2,4},{0,3,4,6}};
    public static int[][] tableauBleu = {{1,1,1,1},{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    public static int[] tableauVert = {-1,1,2,3,4,5,1,2,3,4,5,6};
    public static int[] tableauOrange = {0,0,0,0,0,0,0,0,0,0,0,0};
    public static int[] tableauViolet = {0,0,0,0,0,0,0,0,0,0,0,0};

    private int mligne = -1;
    private int mcolonne = -1;
    private int mcouleur;
    private int mvaleur = -1; // Permet de savoir l'etat de la valeur actuelle de la case

    public static final int BLANC = 0;
    public static final int JAUNE = 1;
    public static final int BLEU = 2;
    public static final int VERT = 3;
    public static final int ORANGE = 4;
    public static final int VIOLET = 5;

    public ImageViewTresFute(Context context) {
        super(context);
    }

    public ImageViewTresFute(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewTresFute(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getColonne()
    {
        return this.mcolonne;
    }

    public int getLigne()
    {
        return this.mligne;
    }

    public int getCouleur()
    {
        return this.mcouleur;
    }

    public int getValeur()
    {
        return this.mvaleur;
    }

    public void setCouleur(int couleur)
    {
        this.mcouleur=couleur;

        // Si couleur vert, orange ou violet, le tableau n'a qu'une seule ligne
        switch (couleur)
        {
            case VERT:
            case ORANGE:
            case VIOLET:
                setLigne(0);
                break;
        }
    }

    public void setColonne(int colonne)
    {
        this.mcolonne=colonne;

        // Affecter la valeur par defaut si ligne et colonne sont attribué
        if (getLigne() != -1 && getValeur() == -1)
        {
            init_valeur();
        }
    }

    public void setLigne(int ligne)
    {
        this.mligne=ligne;

        // Affecter la valeur par defaut si ligne et colonne sont attribué
        if (getColonne() != -1 && getValeur() == -1)
        {
            init_valeur();
        }
    }

    public void setValeur(int valeur)
    {
        this.mvaleur = valeur;
    }

    public void afficheImage(int valeuraAfficher)
    {
        //System.out.println("ImageViewTresFute:afficheImage ");
        switch (valeuraAfficher)
        {
            case 0:
                if (getCouleur()== ORANGE && (getColonne() == 4 || getColonne() == 7 || getColonne() == 9))
                    this.setImageResource(R.drawable.tres_fute_2_orange);
                else if (getCouleur()== ORANGE && getColonne() == 11)
                        this.setImageResource(R.drawable.tres_fute_3_orange);
                else
                    this.setImageResource(R.drawable.tres_fute_case_blanche);
                break;
            case 1:
                if (getCouleur() == VERT)
                    this.setImageResource(R.drawable.tres_fute_1_vert);
                else
                    this.setImageResource(R.drawable.tres_fute_1);
                break;
            case 2:
                if (getCouleur() == VERT)
                    this.setImageResource(R.drawable.tres_fute_2_vert);
                else
                    this.setImageResource(R.drawable.tres_fute_2);
                break;
            case 3:
                if (getCouleur() == VERT)
                    this.setImageResource(R.drawable.tres_fute_3_vert);
                else
                    this.setImageResource(R.drawable.tres_fute_3);
                break;
            case 4:
                if (getCouleur() == VERT)
                    this.setImageResource(R.drawable.tres_fute_4_vert);
                else
                    this.setImageResource(R.drawable.tres_fute_4);
                break;
            case 5:
                if (getCouleur() == VERT)
                    this.setImageResource(R.drawable.tres_fute_5_vert);
                else
                    this.setImageResource(R.drawable.tres_fute_5);
                break;
            case 6:
                if (getCouleur() == VERT)
                    this.setImageResource(R.drawable.tres_fute_6_vert);
                else
                    this.setImageResource(R.drawable.tres_fute_6);
                break;
            case 7:
                this.setImageResource(R.drawable.tres_fute_7);
                break;
            case 8:
                this.setImageResource(R.drawable.tres_fute_8);
                break;
            case 9:
                this.setImageResource(R.drawable.tres_fute_9);
                break;
            case 10:
                this.setImageResource(R.drawable.tres_fute_10);
                break;
            case 11:
                this.setImageResource(R.drawable.tres_fute_11);
                break;
            case 12:
                this.setImageResource(R.drawable.tres_fute_12);
                break;
            case 15:
                this.setImageResource(R.drawable.tres_fute_15);
                break;
            case 18:
                this.setImageResource(R.drawable.tres_fute_18);
                break;
            default:
                this.setImageResource(R.drawable.tres_fute_barre);
                break;

        }
    }

   public void onClick(int valeurDuClick) {
       System.out.println("ImageViewTresFute:onClick ");
       switch (getCouleur())
       {
           case JAUNE:
               onClickCroix(getValeurTableauJaune());
               break;
           case BLEU:
               onClickCroix(getValeurTableauBleu());
               break;
           case VERT:
               onClickCroix(getValeurTableauVert());
               break;
           case ORANGE:
               onClickOrange(valeurDuClick);
               break;
           case VIOLET:
               onClickValeur(valeurDuClick);
               break;
       }
   }

    private void onClickCroix(int valeur) {
        if (valeur == getValeur()) {
            afficheImage(13);
            setValeur(0);
        }
        else {
            afficheImage(valeur);
            setValeur(valeur);
        }
    }

    private void onClickValeur(int pas) {
        int valeur = getValeur();

        if (valeur == (6*pas))
            valeur=pas=0;

        afficheImage(valeur+pas);
        setValeur(valeur+pas);
    }

    private void onClickOrange(int valeurDe)
    {
        switch (getColonne())
        {
            case 4:
            case 7:
            case 9:
                onClickValeur(2*valeurDe);
                break;
            case 11:
                onClickValeur(3*valeurDe);
                break;
            default:
                onClickValeur(1*valeurDe);
                break;
        }
    }

    private int getValeurTableauJaune()
    {
        return tableauJaune[getLigne()][getColonne()];
    }

    private int getValeurTableauBleu()
    {
        return tableauBleu[getLigne()][getColonne()];
    }

    private int getValeurTableauVert()
    {
        return tableauVert[getColonne()];
    }

    private int getValeurTableauOrange()
    {
        return tableauOrange[getColonne()];
    }

    private int getValeurTableauViolet()
    {
        return tableauViolet[getColonne()];
    }

    private void init_valeur()
    {
        switch (getCouleur()) {
            case JAUNE:
                mvaleur=getValeurTableauJaune();
                break;
            case BLEU:
                mvaleur=getValeurTableauBleu();
                break;
            case VERT:
                mvaleur=getValeurTableauVert();
                break;
            case ORANGE:
                mvaleur=getValeurTableauOrange();
                break;
            case VIOLET:
                mvaleur=getValeurTableauViolet();
                break;
        }
        afficheImage(mvaleur);
    }
}
