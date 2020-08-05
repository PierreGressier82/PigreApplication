package com.pigredorou.topquizz.modele;

import java.util.List;

public class Question {
    private String mQuestion;
    private List<String> mListeChoix;
    private int mIndexReponse;

    public Question(String question, List<String> listeChoix, int indexReponse) {
        mQuestion = question;
        mListeChoix = listeChoix;
        mIndexReponse = indexReponse;

    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getListeChoix() {
        return mListeChoix;
    }

    public void setListeChoix(List<String> listeChoix) {
        if (listeChoix == null)
            throw new IllegalArgumentException("Liste de question vide !");
        mListeChoix = listeChoix;
    }

    public int getIndexReponse() {
        return mIndexReponse;
    }

    public void setIndexReponse(int indexReponse) {
        if (indexReponse < 0 || indexReponse >= mListeChoix.size())
            throw new IllegalArgumentException("Réponse incohérente");

        mIndexReponse = indexReponse;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mListeChoix=" + mListeChoix +
                ", mIndexReponse=" + mIndexReponse +
                '}';
    }
}
