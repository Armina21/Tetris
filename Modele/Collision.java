package Modele;

public class Collision {

    private Grille g;
    

    public Collision(Grille g)
    {
        this.g = g;
    }

    public boolean CollisionBas(int x, int y)
    {
        if(g.getGrille(x, y) == 2)
        {
            return true;
        }
        return false;
    }

    public boolean Colli()
    {
        for(int i =0; i <4; i++)
        {
            for(int j =0; j <4; j++)
            {
                if(g.getPieceCourante().getCase(i, j) == 1)
                {
                    int posX = g.getPieceCourante().getx()+i;
                    int posY = g.getPieceCourante().gety()+j;
                    if(CollisionBas(posX, posY))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean Ligne ()
    {
        for (int i = g.TAILLE_Y-1; i>0; i--) {
            
            if(g.getGrille(1, i) != 2)
                return false;
        }
        return true; 
    }

}
