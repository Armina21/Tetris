package Modele;

public class Rotation {
    
    private Grille g;
    private Piece p;

    public Rotation(Grille _grille, Piece _piece)
    {
        this.g = _grille;
        this.p =_piece;
    }

    public void Rot(){
        if (p.getType() == null)
        {
            System.out.println("type null");
            return;
        }
        
        //la grille affiche les pieces en inversées
        switch (p.getType()){
            case I:
                if(p.getrotationnb() == 1)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {1,1,1,1},
                                            {0,0,0,0},
                                            {0,0,0,0}});
                    p.setrotationnb(2);
                }
                else
                {
                    p.setCases(new int[][] {{0,1,0,0},
                                            {0,1,0,0},
                                            {0,1,0,0},
                                            {0,1,0,0}});
                    p.setrotationnb(1);
                    break;
                }
                break;
            case O:
                break;

            case T:
                if (p.getrotationnb() == 1)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,0,0,0},
                                            {1,1,1,0},
                                            {0,1,0,0}});
                    p.setrotationnb(2);
                    break;
                }
                else if (p.getrotationnb() == 2)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,0,0},
                                            {1,1,0,0},
                                            {0,1,0,0}});
                    p.setrotationnb(3);
                    break;
                }
                else if (p.getrotationnb() ==3)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,0,0},
                                            {1,1,1,0},
                                            {0,0,0,0}});
                    p.setrotationnb(4);
                    break;
                }
                else
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,0,0},
                                            {0,1,1,0},
                                            {0,1,0,0}});
                    p.setrotationnb(1);
                    break;
                }

            case S:
                if (p.getrotationnb() ==1)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,1,0},
                                            {1,1,0,0},
                                            {0,0,0,0}});
                    p.setrotationnb(2);
                    break;
                }
                else
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {1,0,0,0},
                                            {1,1,0,0},
                                            {0,1,0,0}});
                    p.setrotationnb(1);
                }
                break;  
            
            case Z:
                if (p.getrotationnb() == 1)
                {
                    p.setCases(new int[][]{{0,0,0,0},
                                        {0,1,0,0},
                                        {1,1,0,0},
                                        {1,0,0,0}});
                    p.setrotationnb(2);
                    break;
                }
                else
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                         {1,1,0,0},
                                         {0,1,1,0},
                                         {0,0,0,0}});
                    p.setrotationnb(1);
                    break;
                }
            
            case L:
                if (p.getrotationnb() == 1)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {1,0,0,0},
                                            {1,1,1,0},
                                            {0,0,0,0}});
                    p.setrotationnb(2);
                    break;
                }
                else if (p.getrotationnb() == 2)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,1,0},
                                            {0,1,0,0},
                                            {0,1,0,0}});
                    p.setrotationnb(3);
                    break;
                }
                else if (p.getrotationnb() == 3)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,0,0,0},
                                            {1,1,1,0},
                                            {0,0,1,0}});
                    p.setrotationnb(4);
                    break;
                }
                else
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,0,0},
                                            {0,1,0,0},
                                            {1,1,0,0}});
                    p.setrotationnb(1);
                    break;
                }
            
            case J:
                
                if (p.getrotationnb() == 1)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,0,0,0},
                                            {1,1,1,0},
                                            {1,0,0,0}});
                    p.setrotationnb(2);
                    break;
                }
                else if (p.getrotationnb() == 2)
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {1,1,0,0},
                                            {0,1,0,0},
                                            {0,1,0,0}});
                    p.setrotationnb(3);
                    break;
                }
                else if (p.getrotationnb() == 3)
                    
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,0,1,0},
                                            {1,1,1,0},
                                            {0,0,0,0}});
                    p.setrotationnb(4);
                    break;
                }
                else
                {
                    p.setCases(new int[][] {{0,0,0,0},
                                            {0,1,0,0},
                                            {0,1,0,0},
                                            {0,1,1,0}});
                    p.setrotationnb(1);
                    break;
                }  
        }
    }
}
