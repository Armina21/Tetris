package Modele;

public class Points {
    
    private Grille grille;
    private int score_total;
    private int score_tempo;


    public Points(Grille _grille)
    {
        this.score_total = 0;
        this.score_tempo = 0;
        this.grille = _grille;
    }

    public int getScore()
    {
        return this.score_total;
    }

    public void setScore(int _score)
    {
        this.score_total = _score;
    }

    public void addScore(int _score)
    {
        this.score_total += _score;
    }

    public int getScoreTempo()
    {
        return this.score_tempo;
    }

    public void setScoreTempo(int _scoretempo)
    {
        this.score_tempo = _scoretempo;
    }

    public void ajoutePoint(){
        int s= grille.descendLigne();
        addScore(s*150);
    }
}
