package Modele;


public class Piece {
    
    private final enumCouleur couleur = enumCouleur.values()[Tool.monRandom(0, 6)];
    private int[][] cases;
    private TypePiece type;
    private Grille grille;
    private int x = 4;
    private int y = 0;
    private int dY = 1;
    private int rotation_nombre = 0;
    private Collision c;
    private Rotation r;
    private boolean est_mystere = false;
    

    public Piece(TypePiece type, Grille _grille) {

        this.c = new Collision(_grille);
        this.rotation_nombre = 1;
        this.grille = _grille;
        this.r = new Rotation(_grille, this);
        //cases = new Case[3][3];         
        switch (type) {
            case I:
                this.cases = new int[][] {{0,1,0,0},
                                        {0,1,0,0},
                                        {0,1,0,0},
                                        {0,1,0,0}};
                this.type = TypePiece.I;
                this.rotation_nombre = 1;
                break;
        
            case O:
                this.cases = new int[][] {{0,0,0,0},
                                        {1,1,0,0},
                                        {1,1,0,0},
                                        {0,0,0,0}};
                this.type = TypePiece.O;
                break;
            
            case S:
                this.cases = new int[][] {{0,0,0,0},
                                        {1,0,0,0},
                                        {1,1,0,0},
                                        {0,1,0,0}};
                this.type = TypePiece.S;
                this.rotation_nombre = 1;
                break;
        
            case Z:
                this.cases = new int[][] {{0,0,0,0},
                                        {0,1,0,0},
                                        {1,1,0,0},
                                        {1,0,0,0}};
                this.type = TypePiece.Z;
                this.rotation_nombre = 2;
                break;
        
            case J:
                this.cases = new int[][] {{0,0,0,0},
                                        {1,1,0,0},
                                        {0,1,0,0},
                                        {0,1,0,0}};
                this.type = TypePiece.J;
                this.rotation_nombre = 3;
                break;
        
            case L:
                this.cases = new int[][] {{0,0,0,0},
                                        {0,1,1,0},
                                        {0,1,0,0},
                                        {0,1,0,0}};
                this.type = TypePiece.L;
                this.rotation_nombre = 3;
                break;
        
            case T:
                this.cases = new int[][] {{0,0,0,0},
                                        {0,1,0,0},
                                        {1,1,0,0},
                                        {0,1,0,0}};
                this.type = TypePiece.T;
                this.rotation_nombre = 3;

            default:
                
                break;
        }

        int mystere = Tool.monRandom(0, 4);
        if(mystere == 1)
            this.est_mystere = true;
        else
            this.est_mystere = false;

        
        
    }


    public void action_gauche(TypePiece t) {
        
        int ind = 2;
        switch (t) {
            case I: if(getrotationnb() == 1)
                        ind = 3;
                break;
            case T: if(getrotationnb() == 2)
                        ind = 1;
                break;

            case L: if(getrotationnb() == 4 || getrotationnb() == 2)
                        ind = 1;
                break;
            
            case J: if(getrotationnb() == 4)
                        ind = 1;
                break;

            default:
            ind = 2;
                break;
        }

        if(grille.validationPosition(x-ind, y))
            x--;
    }


    public void action_droite(TypePiece t) {
        int ind = 3;

        switch (t) {
            case I: if(getrotationnb() == 2)
                        ind = 2;
                    if(getrotationnb() == 1)
                        ind = 4;
                    
                break;

            case T: if(getrotationnb() == 2)
                        ind = 3;
                    if(getrotationnb() == 4)
                        ind = 3;
                break;

            case Z: if(getrotationnb() == 2)
                        ind = 4;
                break;

            case S: if(getrotationnb() == 1)
                        ind = 4;
                break;

            case L: if(getrotationnb() == 3 ||getrotationnb() == 1)
                    ind = 4;
                break;

            case J: 
                    ind = 4;
                break;

            default:
            ind = 3;
                break;
        }

        if(grille.validationPosition(x+ind, y))
        {
            x++;
            ind = 4;
        }
            
    }

    public void action_descente()
    {
        if(grille.validationPosition(x, y-2) )
            dY = 2;
    }


    public boolean verifdescente()
    {
        if(grille.getCollision().CollisionBas(x, y-4))
            return false;
        return true;
    }

    public void action_rotation(){
        System.out.println("rotation");
        
        r.Rot();
    } 
    
    public void run() {
        int nextY = y;
        int nextX = x;

        nextY += dY;

        //if (grille.collisionBas(nextX, nextY-1, type) || grille.validationPosition(nextX, nextY)) {
        if(!c.Colli() && grille.validationPosition(nextX, nextY))
        {
            y = nextY;
            x = nextX;
            if(getDy() == 2 && verifdescente())
            {
                setDy(1);
            
            }    
        } else {
            dY = 0;
            
           
        }
    }

    public Piece getPiece()
    {
        return this;
    }

    public enumCouleur getCouleur() {
        return couleur;
    }

    public int[][] getCases()
    {
        return cases;
    }

    public void setCases(int[][] m)
    {
        this.cases = m;
    }

    public int getCase(int x, int y)
    {
        return cases[x][y];
    }

    public TypePiece getType() {
        return type;
    }

    public int getx()
    {
        return this.x;
    }

    public int gety()
    {
        return this.y;
    }

    public int getrotationnb()
    {
        return rotation_nombre;
    }

    public void setrotationnb(int i)
    {
        this.rotation_nombre = i;
    }

    public boolean getEst_mystere()
    {
        return this.est_mystere;
    }

    public void setEst_mystere(boolean b)
    {
        this.est_mystere = b;
    }

    public int getDy()
    {
        return this.dY;
    }
    public void setDy(int dy)
    {
        this.dY = dy;
    }
   
}
