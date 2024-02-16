package Modele;


import java.util.Observable;
import java.awt.*;

public class Grille extends Observable implements Runnable {

    public final int TAILLE_X = 13;
    public final int TAILLE_Y = 21;
    private enumCouleur [][] grillecouleurs;
    private enumCouleur [][] grillepiecesuivante;
    private enumCouleur [][] grillepiecesuivante2;
    private enumCouleur [][] grillepiecesuivante3;
    private int[][] grille;
    private int[][] grille2;
    private int [][] grille3;
    private int [][] grille4;
    private Collision c;
    private Points points;
    private Niveau level;
    private OrdonnanceurSimple ordonnanceur;
    private boolean pause = false;
    private boolean jeu_commence = false;

    private Piece piece = new Piece(TypePiece.values()[Tool.monRandom(0, 6)], this);
    private PieceSuivante pieceSuivante = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);
    private PieceSuivante pieceSuivante2 = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);
    private PieceSuivante pieceSuivante3 = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);

    public Grille() {
        int i,j;
        //this.ordonnanceur.start();
        this.piece.setEst_mystere(false);
        grille = new int[TAILLE_X][TAILLE_Y];
        grillecouleurs = new enumCouleur[TAILLE_X][TAILLE_Y];
        grille2 = new int[4][4];
        grille3 = new int[4][4];
        grille4 = new int[4][4];
        grillepiecesuivante = new enumCouleur[4][4];
        grillepiecesuivante2 = new enumCouleur[4][4];
        grillepiecesuivante3 = new enumCouleur[4][4];
        c = new Collision(this);
        this.points= new Points(this);
        this.level = new Niveau();

        for (i = 0; i < TAILLE_X; i++) {
            for (j = 0; j < TAILLE_Y-1; j++) {
                grillecouleurs[i][j] = enumCouleur.NOIR;
            }
        }

        //derniere ligne devient occupé
        for (i = 0; i < TAILLE_X; i++) 
        {
            j=19;
            grille[i][j] = 2;
        }

        //grille de la piece suivante afficher a droite
        for(i = 0; i< 4; i++)
        {
            for(j = 0; j< 4; j++)
            {
                grillepiecesuivante[i][j] = enumCouleur.NOIR;
                grillepiecesuivante2[i][j] = enumCouleur.NOIR;
                grillepiecesuivante3[i][j] = enumCouleur.NOIR;
            }
        }
        this.ordonnanceur = new OrdonnanceurSimple(this);
        
    }

    public boolean validationPosition(int _nextX, int _nextY) {

        return (_nextY>=-1 && _nextY <= TAILLE_Y-1 && _nextX>=0 && _nextX < TAILLE_X-1);
    }


    public void nouvellePiece()
    {
        if(c.Colli())
        {
            ajoutCasesDansGrille();
            points.ajoutePoint();
            piece = getPieceSuivante();
            if(piece.getEst_mystere())
            {
                piece.setEst_mystere(false);
            }
            pieceSuivante = getPieceSuivante2();
            pieceSuivante2 = getPieceSuivante3();
            setPieceSuivante3();
            changePieceSuivanteGrille();
            afficheGrille();
            System.out.println("points" + points.getScore());
        } 
    }

    public void changePieceSuivanteGrille()
    {   
        for(int i = 0; i< 4; i++)
        {
            for(int j = 0; j< 4; j++)
            {
                setGrille2(i,j, pieceSuivante.getCase(i,j) ) ;
                grillepiecesuivante[i][j] = piece.getCouleur();
                setGrille3(i,j, pieceSuivante2.getCase(i,j) ) ;
                grillepiecesuivante2[i][j] = pieceSuivante.getCouleur();
                setGrille4(i,j, pieceSuivante3.getCase(i,j) ) ;
                grillepiecesuivante3[i][j] = pieceSuivante2.getCouleur();
                //System.out.print(getGrille(j, i));
            }
        }
    }

    public void ajoutCasesDansGrille()
    {
        //grille[piece.getx()][piece.gety()] = 1;
        for(int i = piece.getx(); i < piece.getx()+4; i++)
        {
            for(int j = piece.gety(); j < piece.gety()+4; j++)
            {
                if(piece.getCase(i-piece.getx(), j-piece.gety()) == 1)
                {
                    System.out.println("i = " + i + " j = " + j);   
                    setGrille(i, j, 2);
                    grillecouleurs[i][j] = piece.getCouleur();
                }
            }
        }
    }

    public void afficheGrille() {
        for (int i = TAILLE_Y-1; i>0; i--) {
            for (int j = 0 ; j <TAILLE_X; j++) {
                System.out.print(getGrille(j,i));
            }
            System.out.print("\n"); // Utilisez println pour passer à la ligne après chaque colonne de la grille
        }
        System.out.print("\n");
    }
    
    public int verifUneLigne(int y)
    {
        int ind = 0;
        for(int i =2; i< TAILLE_X; i++)
        {
            if(getGrille(i, y) ==2)
            {
                ind++;
            }
        }
        System.out.print("i " + ind);
        return ind;
    }
    
    public int descendLigne()
    {
        int i = TAILLE_Y-1;
        boolean verif = false;
        int sommeLigne=0;
        do{
            //premiere boucle pour parcourir toute la grille
            do{
                //deuxieme boucle qui verifie que la nouveau ligne est complete ou non
                //si oui alors on relance le processus sinon on sort de la boucle et on parcoure la grille
                verif = false;
                if(verifUneLigne(i) == 10)
                {  
                    supprimeLigne(i);
                    remplaceLigne(i);
                    sommeLigne ++;
                }  
                else{
                    verif =true;
                }      
            }while(verif != true);
            i--;
        }while(i>0);
        return sommeLigne;
    }


    public void supprimeLigne(int h)
    {
        for(int j =2; j< TAILLE_X; j++)
        {
            setGrille(j, h+1, 0);
            setGrilleCoCase(j, h+1, enumCouleur.NOIR);
        }
    }

    public void remplaceLigne(int h1)
    {
        int h = h1;
        do{
            for(int j =2; j< TAILLE_X; j++)
            {
                setGrille(j, h+1, getGrille(j, h-1));
                setGrilleCoCase(j, h+1, getGrilleCoCase(j, h));
                //setGrille(j, h, 5);
                setGrilleCoCase(j, h, enumCouleur.NOIR); 
            } 
            h--;    
        }while(verifUneLigne(h-1)!=0);
    }

    public void action_gauche(){
        piece.action_gauche(piece.getType());
    }

    public void action_droite(){
        piece.action_droite(piece.getType());
    }

    public void action_rotation(){
        piece.action_rotation();
    }

    public void action_descente()
    {
        piece.action_descente();
    }

    public void pauseOrdonnanceur() {
        ordonnanceur.pause();
    }

    public void resumeOrdonnanceur() {
        ordonnanceur.resumeThread();
    }

    public void MonterNiveau(){
        if(points.getScoreTempo() / 400 >= 1)
        {
            level.addNiveau(1);
            points.setScoreTempo(points.getScoreTempo() - 400);
            ordonnanceur.setDescente();
        }
    }

    public void recommencer(){
        int i,j;
        this.ordonnanceur = new OrdonnanceurSimple(this);
        this.ordonnanceur.start();

        grille = new int[TAILLE_X][TAILLE_Y];
        grillecouleurs = new enumCouleur[TAILLE_X][TAILLE_Y];
        grille2 = new int[4][4];
        grille3 = new int[4][4];
        grille4 = new int[4][4];
        grillepiecesuivante = new enumCouleur[4][4];
        grillepiecesuivante2 = new enumCouleur[4][4];
        grillepiecesuivante3 = new enumCouleur[4][4];
        c = new Collision(this);

        for (i = 0; i < TAILLE_X; i++) {
            for (j = 0; j < TAILLE_Y-1; j++) {
                grillecouleurs[i][j] = enumCouleur.NOIR;
            }
        }

        //derniere ligne devient occupé
        for (i = 0; i < TAILLE_X; i++) 
        {
            j=19;
            grille[i][j] = 2;
        }

        //grille de la piece suivante afficher a droite
        for(i = 0; i< 4; i++)
        {
            for(j = 0; j< 4; j++)
            {
                grillepiecesuivante[i][j] = enumCouleur.NOIR;
                grillepiecesuivante2[i][j] = enumCouleur.NOIR;
                grillepiecesuivante3[i][j] = enumCouleur.NOIR;
            }
        }

        piece = new Piece(TypePiece.values()[Tool.monRandom(0, 6)], this);
        pieceSuivante = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);
        pieceSuivante2 = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);
        pieceSuivante3 = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);
        points = new Points(this);
        level = new Niveau();
    }
   

    public void run() {

        piece.run();
        setChanged(); // setChanged() + notifyObservers() : notification de la vue pour le rafraichissement
        notifyObservers();
        nouvellePiece();
        //afficheGrille();
        changePieceSuivanteGrille();
        MonterNiveau();
        
       
    }
        
    public OrdonnanceurSimple getOrdonnanceur() {
        return ordonnanceur;
    }

    public TypePiece getTypePiece()
    {
        return piece.getType();
    }

    public Piece getPieceCourante() {
        return piece;
    }

    public PieceSuivante getPieceSuivante()
    {
        return pieceSuivante;
    }

    public PieceSuivante getPieceSuivante2()
    {
        return pieceSuivante2;
    }

    public PieceSuivante getPieceSuivante3()
    {
        return pieceSuivante3;
    }

    public void setPieceSuivante3()
    {
        pieceSuivante3 = new PieceSuivante(TypePiece.values()[Tool.monRandom(0, 6)], this);
    }

    public int getGrille(int i, int j)
    {
        return grille[i][j];
    }

    public int getGrille2(int i, int j)
    {
        return grille2[i][j];
    }

    public void setGrille(int i, int j, int k)
    {
        grille[i][j-1] = k;
    }

    public void setGrille2(int i, int j, int p)
    {
        this.grille2[i][j] = p;
    }

    public void setGrille3(int i, int j, int p)
    {
        this.grille3[i][j] = p;
    }

    public void setGrille4(int i, int j, int p)
    {
        this.grille4[i][j] = p;
    }


    public Collision getCollision()
    {
        return this.c;
    }

    //retourne le tableau de couleur
    public enumCouleur[][] getGrilleCouleurs() {
        return grillecouleurs;
    }

    public enumCouleur[][] getGrillepiecesuivante() {
        return grillepiecesuivante;
    }

    public enumCouleur getGrilleCoCase(int i, int j)
    {
        return grillecouleurs[i][j];
    }

    public void setGrilleCoCase(int i, int j, enumCouleur c)
    {
        this.grillecouleurs[i][j] = c;
    }

    public boolean getPause()
    {
        return this.pause;
    }

    public void setPause()
    {
        if(this.pause == false)
        {
            this.pause = true;
            
        }
        else
        {
            this.pause = false;
            
        }
    }


    public Points getPoints()
    {
        return points;
    }

    public Niveau getNiveau()
    {
        return level;
    }

    public boolean getJeuCommence()
    {
        return this.jeu_commence;
    }
    
    public void setJeuCommence()
    {
        this.jeu_commence = true;
    }

}
