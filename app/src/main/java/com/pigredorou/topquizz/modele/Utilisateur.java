package com.pigredorou.topquizz.modele;

public class Utilisateur {
    private String mPrenom;

    public String getPrenom() {
        return mPrenom;
    }

    public void setPrenom(String prenom) {
        mPrenom = prenom;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "mPrenom='" + mPrenom + '\'' +
                '}';
    }
}
