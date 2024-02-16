package Modele;

public class Niveau {
    
    private int niveau;


    public Niveau()
    {
        this.niveau = 1;
    }

    public int getNiveau()
    {
        return this.niveau;
    }

    public void setNiveau(int _niveau)
    {
        this.niveau = _niveau;
    }

    public void addNiveau(int _niveau)
    {
        this.niveau += _niveau;
    }
}
